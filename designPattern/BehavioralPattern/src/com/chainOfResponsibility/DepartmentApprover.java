package com.chainOfResponsibility;

public class DepartmentApprover extends Approver {
    public DepartmentApprover(String name) {
        super(name);
    }

    @Override
    protected void processRequest(PurchaseRequest request) {
        if (request.getPrice() <= 10000){
            System.out.println("请求编号为"+request.getId()+
                    " 需要"+request.getType()+", 已经被"+ getName()+"处理");
        }else{
            getApprover().processRequest(request);
        }
    }
}
