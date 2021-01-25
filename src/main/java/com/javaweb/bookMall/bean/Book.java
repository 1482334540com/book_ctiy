package com.javaweb.bookMall.bean;

public class Book {
    public static void main(String[] args) {
        Book book = new Book(1,"root","huyu",12, 12 ,12 ,null);
        System.out.println(book);
    }

    private  Integer id;
    private String name;
    private  String author;
    private Integer  price;
    private  Integer sales;
    private  Integer stock;
    private  String img ="/book_ctiy/book/img/timg.jpg";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
//        img不能为空，如果为空就使用默认值
        if(img!=null&&!"".equals(img)){
            this.img = img;
        }

    }

    public Book() {

    }

    public Book(Integer id, String name, String author, Integer price, Integer sales, Integer stock, String img) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        if(img!=null&&!"".equals(img)){
            this.img = img;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + img + '\'' +
                '}';
    }
}
