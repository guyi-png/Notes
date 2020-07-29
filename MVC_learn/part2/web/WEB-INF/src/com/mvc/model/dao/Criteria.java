package com.mvc.model.dao;

// 查询条件, 模糊查询
public class Criteria {
    private String name;
    private String address;
    private String phone;

    public Criteria(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        if (name == null){
            return "%%";
        }else{
            return "%"+name+"%";
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        if (address == null){
            return "%%";
        }else{
            return "%"+address+"%";
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        if (phone == null){
            return "%%";
        }else{
            return "%"+phone+"%";
        }
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
