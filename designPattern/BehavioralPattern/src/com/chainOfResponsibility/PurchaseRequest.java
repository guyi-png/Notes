package com.chainOfResponsibility;

/**
 * 请求方
 */
public class PurchaseRequest {
    private Integer id;
    private String type;
    private double price;

    public PurchaseRequest(Integer id, String type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}
