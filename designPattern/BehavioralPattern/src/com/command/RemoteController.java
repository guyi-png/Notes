package com.command;


public class RemoteController {
    // 开 的一组命令
    private Command[] onCommands;
    // 关 的一组命令
    private Command[] offCommands;
    // 取消命令
    private Command undoCommand;

    public RemoteController(){
        int unitButtonNum = 5;
        onCommands = new Command[unitButtonNum];
        offCommands = new Command[unitButtonNum];
        for (int i =0; i < unitButtonNum; i++){
            onCommands[i] = new NoCommand();
            offCommands[i] = new NoCommand();
        }
    }

    public void setCommand(int unit, Command onCommand, Command offCommand){
        // 设立一组按钮
        onCommands[unit] = onCommand;
        offCommands[unit] = offCommand;
    }

    // 按 开 的按钮
    public void onButtonWasPushed(int unit){
        onCommands[unit].execute();
        // 记录 开 的那个按钮， 用于撤销
        undoCommand = onCommands[unit];
    }

    // 按 关 的按钮
    public void offButtonWasPushed(int unit){
        offCommands[unit].execute();
        // 记录 关 的那个按钮， 用于撤销
        undoCommand = offCommands[unit];
    }

    // 按 撤销 按钮
    public void undoButtonWasPushed(){
        undoCommand.undo();
    }
}
