package JavaRush.MiniProjects.others.SomeDialog.model;

import JavaRush.MiniProjects.others.SomeDialog.service.UserNameChecker;
import JavaRush.MiniProjects.others.SomeDialog.service.Util.ConsoleHelper;
import JavaRush.MiniProjects.others.SomeDialog.message.Message;
import JavaRush.MiniProjects.others.SomeDialog.message.enums.MessageType;
import JavaRush.MiniProjects.others.SomeDialog.connection.LocalChatConnection;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;


public class User implements Runnable {

    private final String name;
    private LocalChatConnection connection;
    private volatile boolean userJoined = false;
    private final LinkedList<String> speechList;
    private volatile boolean mayWrite = false;

    public User(String name) {
        // UserNameChecker.addNewUser() возвращает true если не существует юзера с таким именем.
        if (UserNameChecker.addNewUser(name)) {
            this.name = name;
        } else {
            throw new RuntimeException("User с именем " + name + " уже существует");
        }
        speechList = new LinkedList<>();
    }

    @Override
    public void run() {
        joinToTheChat();
    }

    private void joinToTheChat() {

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

        while (userJoined && !speechList.isEmpty()) {
            while (userJoined &&!mayWrite) {
                LockSupport.parkNanos(10000);
            }
            try {
                connection.send(new Message(MessageType.TEXT, speechList.pollFirst()));
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Ошибка при отправке сообщения. Соединение будет закрыто.");
                userJoined = false;
            }
            setMayWrite(false);
        }

    }

    private class SocketThread extends Thread {
        @Override
        public void run() {
            try {
                User.this.connection = new LocalChatConnection();
                handShake();
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка во время присоединения " + name);
            }
        }

        private void handShake() throws IOException, ClassNotFoundException {

            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.NAME_REQUEST) {
                    String clientName = User.this.getName();
                    connection.send(new Message(MessageType.USER_NAME, clientName));
                } else if (message.getType() == MessageType.NAME_ACCEPTED) {
                    userJoined = true;
                    break;
                }
            }
        }
    }


    /**
     *
     * @throws NullPointerException если speech==null
     */
    public void addSpeech(String... speech) {
        speechList.addAll(List.of(speech));
        //todo case when no arguments: sitcomActorObj.addSpeech();
    }

    public void clearSpeechList(){
        speechList.clear();
    }

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

    public String getName() {
        return name;
    }

    public boolean isUserJoined() {
        return userJoined;
    }

    //happens before: один из вызовов этого сеттера из класса Server будет после создания нового объекта User
    //happens before: один из вызовов этого сеттера объектом User obj будет после вызова этого сеттера из Server.go
    public void setMayWrite(boolean mayWrite) {
        this.mayWrite = mayWrite;
    }
}
