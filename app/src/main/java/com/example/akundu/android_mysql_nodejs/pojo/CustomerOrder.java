package com.example.akundu.android_mysql_nodejs.pojo;

public class CustomerOrder {

    public int orderNumber, quantityOrdered, orderLineNumber;
    public double priceEach;
    public String productCode, productName, productLine, productVendor, image;

    public CustomerOrder(int orderNumber, String productCode, int quantityOrdered, double priceEach, int orderLineNumber, String productName, String productLine, String productVendor, String image) {
        this.orderNumber = orderNumber;
        this.productCode = productCode;
        this.quantityOrdered = quantityOrdered;
        this.priceEach = priceEach;
        this.orderLineNumber = orderLineNumber;
        this.productName = productName;
        this.productLine = productLine;
        this.productVendor = productVendor;
        this.image = "https://media.zigcdn.com/media/model/2018/May/apache-rtr160-full-right-side-view_600x300.jpg";//image;
    }
}
