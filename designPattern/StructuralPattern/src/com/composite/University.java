package com.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * University 就是 Composite， 可以管理 College
 */
public class University extends OrganizationComponent{
    // College的集合
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public University(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void print() {
        System.out.println(getName()+":");
        System.out.println("\t"+getDesc());
        for (OrganizationComponent oc : organizationComponents){
            System.out.print("\t");
            oc.print();
        }
    }

    @Override
    protected void add(OrganizationComponent oc) {
        organizationComponents.add(oc);
    }

    @Override
    protected void delete(OrganizationComponent oc) {
        organizationComponents.remove(oc);
    }
}
