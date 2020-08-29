package com.spring.useXml;

import java.util.List;

public interface Cashier {
    void checkout(String username, List<String> isbnList);
}
