package com.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * College 也是 Composite， 可以管理Department
 */
public class College extends OrganizationComponent{
    // Department集合
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public College(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void print() {
        System.out.println(getName()+":");
        System.out.println("\t\t"+getDesc());
        for (OrganizationComponent oc : organizationComponents){
            System.out.print("\t\t");
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
