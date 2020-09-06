package com.chainOfResponsibility;

/**
 * 处理请求方，抽象父类
 */
public abstract class Approver {
    private String name;
    // 用于构成职责链
    private Approver approver;

    public Approver(String name) {
        this.name = name;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    public String getName() {
        return name;
    }

    public Approver getApprover() {
        return approver;
    }

    // 处理请求的方法
    protected abstract void processRequest(PurchaseRequest request);
}
