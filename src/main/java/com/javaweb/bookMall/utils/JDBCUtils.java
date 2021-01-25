package com.javaweb.bookMall.utils;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCUtils {
    private static  String driver ;
    private static String url;
    private static String user;
    private static String password;


    static {
        try {
            Properties properties = new Properties();
            //读取配置文件（注意点，配置文件中不要有空格，配置文件必须放置src路径下）
            InputStream inputStream = com.javaweb.bookMall.utils.WebUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");

            //加载配置文件
            properties.load(inputStream);
            //获取配置文件中的数据
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            //加载数据库链接驱动
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     *  获取数据库连接对象
     * @return 返回数据库连接对象（数据库接连接对象）
     * @throws SQLException
     */

    public static Connection getConnection() throws SQLException{
        //获取数据库连接对象
        Connection  connection =DriverManager.getConnection(url, user, password);
        return  connection;
    }
    /**
     * 关闭查询连接对象资源
     *
     * @param connection 关闭数据库连接对象
     * @param statement 关闭 statement和 PreparedStatement 对象（因为PreparedStatement是statement和的子接口）
     * @param resultSet 关闭查询结果集
     */
    public static void closeStatement (Connection connection, Statement statement, ResultSet resultSet){
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (statement !=null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 关闭 增 删 改  连接对象
     *
     * @param connection  关闭connection对象
     * @param statement   关闭 statement和PreparedStatement对象
     */
    public static void closeStatement (Connection connection,Statement statement){

        if (statement !=null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }



    /**
     *  查询用户名是否可用
     * @param sql
     * @param args
     * @return 用户名可用返回true，不可用返回false
     */
    public Boolean queryUserName(String sql, Object...args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            //获取数据库连接对象
          JDBCUtils.getConnection();
            //传入预编译sql语句
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0;i < args.length;i++){
                preparedStatement.setObject(i + 1, args[i]);
            }
            //返回查询结果集
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return  false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
           JDBCUtils.closeStatement(connection,preparedStatement,resultSet);

        }
        return true;
    }



    /**
     * 通用查询
     *
     * @param clazz 需要查询的类型 类名.Class
     * @param sql 需要查询的sql语句
     * @param args 占位符 可变参数，可以不需要 不传就是查询全部
     * @param <T>  使用泛型机制，让这个查询使用于所有的实体类
     * @return 如果查询到就返回一该对象的list集合 ，没有查询到返回null
     */
    public <T> List<T> QueryForList(Class<T> clazz, String sql, Object... args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            resultSet = preparedStatement.executeQuery();
            // 获取结果集的元数据 :ResultSetMetaData
            ResultSetMetaData rsmd = resultSet.getMetaData();
            // 通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();

            //创建集合对象
            ArrayList<T> list = new ArrayList<T>();
            while (resultSet.next()) {
                T t = clazz.newInstance();
                // 处理结果集一行数据中的每一个列:给t对象指定的属性赋值
                for (int i = 0; i < columnCount; i++) {

                    // 获取列值
                    Object columValue = resultSet.getObject(i + 1);
                    // 获取每个列的列名
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    // 给t对象指定的columnName属性，赋值为columValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    //给指定对象属性名赋值
                    field.set(t, columValue);
                }
                list.add(t);

            }
            //返回查询结果集
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放数据库连接资源
          JDBCUtils.closeStatement(connection,preparedStatement,resultSet);

        }

        return null;
    }
    /**
     *  通用增 删 改 方法
     * @return 返回一个Boolean 如果返回true则修改成功，如果返回false，则修改失败
     */
    public  Boolean GeneralUpdate(String sql ,Object... args){   //sql中占位符的个数与可变形参的长度相同！
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            //1.获取数据库的连接
            connection = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PreparedStatement的实例
            preparedStatement = connection.prepareStatement(sql);
            //3.填充占位符
            for(int i = 0;i < args.length;i++){

                preparedStatement.setObject(i + 1, args[i]);//小心参数声明错误！！
            }
            //4.执行
            count = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{

            //关闭数据连接
         JDBCUtils.closeStatement(connection,preparedStatement);
            if (count>0){
                return true;
            }else{
                return false;
            }

        }
    }

    //测试是否可以成功连接诶数据库
    @Test
    public  void Test() throws SQLException {
        Connection connection= getConnection();
        System.out.println(connection);
    }


}
