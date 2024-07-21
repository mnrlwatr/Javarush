package JavaRush.MiniProjects.others.SomeDialog.service;

import JavaRush.MiniProjects.others.SomeDialog.connection.Connection;
import JavaRush.MiniProjects.others.SomeDialog.model.User;
import JavaRush.MiniProjects.others.SomeDialog.service.Util.ConsoleHelper;
import JavaRush.MiniProjects.others.SomeDialog.message.Message;
import JavaRush.MiniProjects.others.SomeDialog.message.enums.MessageType;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.locks.LockSupport;


public class LocalChatJoiner extends AbstractChatJoiner implements Runnable {

    private final User currentUser;
    private Connection connection;
    private volatile boolean userJoined = false;
    private volatile boolean mayWrite = false;

    public LocalChatJoiner(User currentUser) {
        this.currentUser = currentUser;
        new Thread(this).start();
    }

    @Override
    public void run() {
        join();
    }

    @Override
    public void join() {

        SocketThread socketThread = new SocketThread();
        socketThread.start();
        try {
            socketThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }


        if (!userJoined) {
            ConsoleHelper.writeMessage("failed to join");
        }

        while (userJoined && !currentUser.getSpeechList().isEmpty()) {
            while (userJoined && !mayWrite) {
                LockSupport.parkNanos(10000);
            }
            try {
                connection.send(new Message(MessageType.TEXT, currentUser.getSpeechList().pollFirst()));
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Ошибка при отправке сообщения. Соединение будет закрыто.");
                userJoined = false;
            }
            setMayWrite(false);
        }

    }

    public class SocketThread extends Thread {
        @Override
        public void run() {
            try {
                LocalChatJoiner.this.connection = new Connection(new Socket("localhost", 4004));
                handShake();
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка во время присоединения " + currentUser.getName());
            }
        }

        private void handShake() throws IOException, ClassNotFoundException {

            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.NAME_REQUEST) {
                    String clientName = currentUser.getName();
                    connection.send(new Message(MessageType.USER_NAME, clientName));
                } else if (message.getType() == MessageType.NAME_ACCEPTED) {
                    userJoined = true;
                    break;
                }
            }
        }
    }

    @Override
    public void exit() {
        try {
            connection.send(new Message(MessageType.USER_REMOVED));
        } catch (IOException e) {
            userJoined = false;
            ConsoleHelper.writeMessage("Error, connection will be closed");
        }
        userJoined = false;
    }

    // Getters and Setters

    public boolean isUserJoined() {
        return userJoined;
    }

    //happens before: один из вызовов этого сеттера из класса Server будет после создания нового объекта User
    //happens before: один из вызовов этого сеттера объектом User obj будет после вызова этого сеттера из Server.go
    public void setMayWrite(boolean mayWrite) {
        this.mayWrite = mayWrite;
    }
}
