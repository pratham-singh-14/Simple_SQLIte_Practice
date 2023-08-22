package com.user.khanakhajana.Models;

public class OrderModel {
    int orderImage;
    String orderName,orderNumber,orderPrice,quantity;


    public OrderModel() {
        this.orderImage = orderImage;
        this.quantity = quantity;
        this.orderName = orderName;
        this.orderNumber = orderNumber;
        this.orderPrice = orderPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int orderImage) {
        this.orderImage = orderImage;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }
}
