package com.command;

/**
 * 声明执行命令的接口
 */
public interface Command {
    // 执行命令
    void execute();
    // 取消命令
    void undo();
}
