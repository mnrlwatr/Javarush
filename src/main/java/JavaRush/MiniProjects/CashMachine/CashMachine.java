package JavaRush.MiniProjects.CashMachine;

import JavaRush.MiniProjects.CashMachine.command.CommandExecutor;
import JavaRush.MiniProjects.CashMachine.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {

    /*
    Если запустить main(); кидает исключение java.util.MissingResourceException: Can't find bundle
    for base name JavaRush.MiniProjects.CashMachine.resources.verifiedCards, locale en.
    Но если собрать его как простой java проект (new project->Java->Build system:IntelliJ) то код работает нормально.
    */
    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName() + ".resources.";

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            Operation operation = Operation.LOGIN;
            CommandExecutor.execute(operation);
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        } catch (InterruptOperationException ignored) {
            ConsoleHelper.printExitMessage();
        }
    }
}
