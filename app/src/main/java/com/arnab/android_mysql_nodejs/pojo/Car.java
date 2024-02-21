package com.arnab.android_mysql_nodejs.pojo;


public class Car {
    private String productCode;
    private String productName;
    private String productLine;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private int quantityInStock;
    private Double buyPrice;
    private Double MSRP;
    private String image;

    public Car() {
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public void setProductScale(String productScale) {
        this.productScale = productScale;
    }

    public void setProductVendor(String productVendor) {
        this.productVendor = productVendor;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }


    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setMSRP(Double MSRP) {
        this.MSRP = MSRP;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductLine() {
        return productLine;
    }

    public String getProductScale() {
        return productScale;
    }

    public String getProductVendor() {
        return productVendor;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public Double getMSRP() {
        return MSRP;
    }

    public String getImage() {
        return image;
    }
}