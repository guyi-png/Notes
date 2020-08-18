package com.bookstore.mvc.model.web;

import java.util.List;

/**
 *  记录分页相关
 * @param <T> Book
 */
public class Page<T> {
    // 当前第几页
    private int pageNo;
    // 当前页面需要显示的list
    private List<T> list;
    // 每页显示多少条记录
    private int pageNumber = 2;
    // 所有页面一共有多少条记录
    private long totalNumber;

    // 构造器，只初始化pageNo
    public Page(int pageNo) {
        this.pageNo = pageNo;
    }

    // 由于页数有限，需要验证是否超出页数
    public int getPageNo() {
        if (pageNo < 1){
            pageNo = 1;
        }
        if (pageNo > getPageSize()){
            pageNo = getPageSize();
        }
        return pageNo;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    // 获取总页面数
    public int getPageSize() {
        int pageSize;
        if (totalNumber % pageNumber == 0){
            pageSize = (int) totalNumber/pageNumber;
        }else{
            pageSize = (int) totalNumber/pageNumber + 1;
        }
        return pageSize;
    }

    public void setTotalNumber(long totalNumber) {
        this.totalNumber = totalNumber;
    }

    // 是否有下一页
    public boolean hasNext(){
        return getPageNo() < getPageSize();
    }

    // 是否有上一页
    public boolean hasPrev(){
        return getPageNo() > 1;
    }

    // 获取上一页
    public int getPrevPage(){
        if (hasPrev()){
            return getPageNo() - 1;
        }
        return getPageNo();
    }

    // 获取下一页
    public int getNextPage(){
        if (hasNext()){
            return getPageNo() + 1;
        }
        return getPageNo();
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", list=" + list +
                ", pageNumber=" + pageNumber +
                ", totalNumber=" + totalNumber +
                ", prevPage=" + getPrevPage() +
                ", nextPage=" + getNextPage() +
                '}';
    }
}
