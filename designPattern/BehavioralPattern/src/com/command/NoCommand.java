package com.command;

/**
 * 空命令，
 * 用于初始化每个按钮，当调用空命令时，对象什么都不做, 可以省去空判断
 */
public class NoCommand implements Command {
    @Override
    public void execute() {}

    @Override
    public void undo() {}
}
