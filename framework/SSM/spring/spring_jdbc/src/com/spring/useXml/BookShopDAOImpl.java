package com.spring.useXml;

import org.springframework.jdbc.core.JdbcTemplate;

public class BookShopDAOImpl implements BookShopDAO {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer getBookPriceByIsbn(String isbn) {
        String sql = "SELECT price FROM books WHERE isbn = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, isbn);
    }

    @Override
    public void updateBookStock(String isbn) {
        // 查看数据库中库存是否为0
        String querySql = "SELECT stock FROM book_stock WHERE isbn = ?";
        Integer count = jdbcTemplate.queryForObject(querySql, Integer.class, isbn);
        if (count > 0){
            String sql = "UPDATE book_stock SET stock = stock - ? WHERE isbn = ?";
            jdbcTemplate.update(sql, 1, isbn);
        }else{
            throw new StockException("库存不足");
        }
    }

    @Override
    public void updateUserAccount(String username, int price) {
        // 查看用户的余额是否充足
        String querySql = "SELECT balance FROM account WHERE username = ?";
        Integer balance = jdbcTemplate.queryForObject(querySql, Integer.class, username);
        if (balance >= price){
            String sql = "UPDATE account SET balance = balance - ? WHERE username = ?";
            jdbcTemplate.update(sql, price, username);
        }else{
            throw new AccountException("用户余额不足");
        }
    }
}
