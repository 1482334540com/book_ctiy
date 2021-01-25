package com.javaweb.bookMall.bean;

import java.util.List;

public class Paging<T> {
        //每页显示的数量
        public  final  static  Integer PAGE_SIZE=4;

        //当前页码
        private  Integer pageNo;

        //总页码
        private  Integer pageTotal;

        //当前显示数量
        private  Integer pageSize=PAGE_SIZE;

        //总记录数
        private  Integer PageTotalCount;

        //当前页数据
        private List<T> items;


        //分页请求地址
        private  String url;


    public static Integer getPageSize() {
        return PAGE_SIZE;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return PageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        PageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {

        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Paging(Integer pageNo, Integer pageTotal, Integer pageSize, Integer pageTotalCount, List<T> items, String url) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        PageTotalCount = pageTotalCount;
        this.items = items;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Paging{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", PageTotalCount=" + PageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }

    public Paging() {
    }
}
