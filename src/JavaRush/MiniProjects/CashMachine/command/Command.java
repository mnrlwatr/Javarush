package JavaRush.MiniProjects.CashMachine.command;


import JavaRush.MiniProjects.CashMachine.exception.InterruptOperationException;

interface Command {
    void execute() throws InterruptOperationException;
}
