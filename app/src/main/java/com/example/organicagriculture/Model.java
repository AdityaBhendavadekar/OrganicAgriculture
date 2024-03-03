package com.example.organicagriculture;

public class Model {
    private String productName;
    private String price;
    private int imgname;

    public Model(String productName, String price, int imgname) {
        this.productName = productName;
        this.price = price;
        this.imgname = imgname;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImgname() {
        return imgname;
    }

    public void setImgname(int imgname) {
        this.imgname = imgname;
    }
}
