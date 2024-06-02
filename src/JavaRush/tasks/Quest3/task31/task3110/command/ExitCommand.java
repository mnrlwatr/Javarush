package JavaRush.tasks.Quest3.task31.task3110.command;

import JavaRush.tasks.Quest3.task31.task3110.ConsoleHelper;

public class ExitCommand implements Command {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("До встречи!");
    }
}
