package com.spring1.calculator;

public class Main {
    public static void main(String[] args) {
        Calculator calculatorProxy = CalculatorLoggingProxy.getProxyInstance();
        int result = calculatorProxy.add(1, 2);
        System.out.println("result: "+result);
    }
}
