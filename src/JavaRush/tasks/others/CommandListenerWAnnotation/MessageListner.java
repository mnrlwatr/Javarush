package JavaRush.tasks.others.CommandListenerWAnnotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MessageListner
{
    //Map который хранит как ключ команду
    //А как значение функцию которая будет обрабатывать команду
    private static final Map<String, Method> COMMANDS = new HashMap<>();

    //Объект класса с командами (по сути нужен нам для рефлексии)
    private static final CommandListener LISTENER = new CommandListener();

    static
    {
        //Берем список всех методов в классе CommandListener
        for (Method m : LISTENER.getClass().getDeclaredMethods())
        {
            //Смотрим, есть ли у метода нужная нам Аннотация @Command
            if (m.isAnnotationPresent(Command.class))
            {
                //Берем объект нашей Аннотации
                Command cmd = m.getAnnotation(Command.class);
                //Кладем в качестве ключа нашей карты параметр name()
                //Определенный у нашей аннотации,
                //m — переменная, хранящая наш метод
                COMMANDS.put(cmd.name(), m);

                //Также заносим каждый элемент aliases
                //Как ключ указывающий на тот же самый метод.
                for (String s : cmd.aliases())
                {
                    COMMANDS.put(s, m);
                }
            }
        }
    }

    public void onMessageReceived(MessageReceivedEvent event)
    {

        String message = event.getMessage().toLowerCase();
        if (message.startsWith("бот"))
        {
            try
            {
                String[] args = message.split(" ");
                String command = args[1];
                String[] nArgs = Arrays.copyOfRange(args, 2, args.length);
                Method m = COMMANDS.get(command);
                if (m == null)
                {
                    //(вывод помощи)
                    return;
                }
                Command com = m.getAnnotation(Command.class);
                if (nArgs.length < com.minArgs())
                {
                    //что-то если аргументов меньше чем нужно
                }
                else if (nArgs.length > com.maxArgs())
                {
                    //что-то если аргументов больше чем нужно
                }
                //Через рефлексию вызываем нашу функцию-обработчик
                //Именно потому что мы всегда передаем nArgs у функции должен быть параметр
                //String[] args — иначе она просто не будет найдена;
                m.invoke(LISTENER, nArgs);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                //Вывод списка команд или какого-либо сообщения
                //В случае если просто написать "Бот"
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}