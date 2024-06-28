package JavaRush.tasks.Quest3.task31.task3110.command;

import JavaRush.tasks.Quest3.task31.task3110.ConsoleHelper;
import JavaRush.tasks.Quest3.task31.task3110.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class ZipCommand implements Command {

    public ZipFileManager getZipFileManager() throws Exception{
        ConsoleHelper.writeMessage("Введите полный путь файла архива:");
        Path zipPath = Paths.get(ConsoleHelper.readString());
        return new ZipFileManager(zipPath);
    }
}