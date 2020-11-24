package com.learn.java1;

/**
 * Code:
 *       stack=2, locals=4, args_size=1     // 操作数栈大小为2，局部变量表长度为4
 *          0: bipush        19          //pc寄存器存放0，将19放到操作数栈中
 *          2: istore_1                 // pc寄存器存放2，操作数栈出栈，局部变量表存入19数值
 *          3: bipush        35         // pc寄存器存放3，将35放到操作数栈中
 *          5: istore_2                 // pc寄存器存放5，操作数栈出栈，将35存入局部变量表中
 *          6: iload_1                  // pc寄存器存放6，从局部变量表中取出19，放到操作数栈中
 *          7: iload_2                  // pc寄存器存放7，从局部变量表中取出35，放到操作数栈中
 *          8: iadd                     // pc寄存器存放8，从操作数栈中出栈两个数（19和35），
 *                                  然后通过执行引擎将字节码指令转为机器指令由cpu执行相加后的值放入操作数栈
 *          9: istore_3                 // pc寄存器存放9，操作数栈出栈，将64存入局部变量表中
 *         10: return
 */
public class AddOperationTest {
    public static void main(String[] args) {
        // byte,short,char,boolean在局部变量表中都是以int类型存储
        byte i = 19;
        int j = 35;
        int k = i + j;                                                                                                                    ;
    }
}
