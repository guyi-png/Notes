package com.spring.transaction;

import java.util.List;

public interface Cashier {
    void checkout(String username, List<String> isbnList);
}
