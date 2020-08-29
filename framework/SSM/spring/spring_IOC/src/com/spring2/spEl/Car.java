package com.spring2.spEl;

public class Car {
    private String brand;
    private double price;
    private double carBody;

    public Car() {
        System.out.println("Car's constructor");
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCarBody() {
        return carBody;
    }

    public void setCarBody(double carBody) {
        this.carBody = carBody;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                ", carBody=" + carBody +
                '}';
    }
}
