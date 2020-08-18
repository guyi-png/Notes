package com.bookstore.mvc.model.web;

/**
 * 定义图书筛选条件
 */
public class CriteriaBook {
    private double minPrice = 0;
    private double maxPrice = Integer.MAX_VALUE;
    private int pageNo; //当前页码

    public CriteriaBook(int pageNo){
        this.pageNo = pageNo;
    }

    public CriteriaBook(double minPrice, double maxPrice, int pageNo) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.pageNo = pageNo;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "CriteriaBook{" +
                "minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", pageNo=" + pageNo +
                '}';
    }
}
