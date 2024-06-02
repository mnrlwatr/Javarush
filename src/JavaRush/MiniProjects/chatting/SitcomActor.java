package JavaRush.MiniProjects.chatting;

import JavaRush.MiniProjects.chatting.Util.ClientDAO;
import JavaRush.MiniProjects.chatting.Util.ConsoleHelper;
import JavaRush.MiniProjects.chatting.connection.Message;
import JavaRush.MiniProjects.chatting.connection.MessageType;
import JavaRush.MiniProjects.chatting.connection.TheSceneConnection;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;


public class SitcomActor implements Runnable {

    private final String name;
    private TheSceneConnection connection;
    private volatile boolean actorJoined = false;
    private final LinkedList<String> speechList;
    private volatile boolean maySay = false;

    public SitcomActor(String name) {
        // ClientDAO.addNewClient() возвращает true если не существует актёра с таким именем
        if (ClientDAO.addNewClient(name)) {
            this.name = name;
        } else {
            throw new RuntimeException("Актёр с именем " + name + " уже существует");
        }
        speechList = new LinkedList<>();
    }

    @Override
    public void run() {
        joinToTheScene();
    }

    public void joinToTheScene() {

        SocketThread socketThread = new SocketThread();
        socketThread.start();
        try {
            socketThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }


        if (!actorJoined) {
            ConsoleHelper.writeMessage("failed to join");
        }

        while (actorJoined && !speechList.isEmpty()) {
            while (actorJoined&&!maySay) {
                LockSupport.parkNanos(10000);
            }
            try {
                connection.send(new Message(MessageType.TEXT, speechList.pollFirst()));
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Ошибка при отправке сообщения. Соединение будет закрыто.");
                actorJoined = false;
            }
            setMaySay(false);
        }

    }

    private class SocketThread extends Thread {
        @Override
        public void run() {
            try {
                SitcomActor.this.connection = new TheSceneConnection();
                handShake();
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка во время присоединение " + name);
            }
        }

        private void handShake() throws IOException, ClassNotFoundException {

            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.NAME_REQUEST) {
                    String clientName = SitcomActor.this.getName();
                    connection.send(new Message(MessageType.USER_NAME, clientName));
                } else if (message.getType() == MessageType.NAME_ACCEPTED) {
                    actorJoined = true;
                    break;
                }
            }
        }
    }


    /**
     * Речи будут сказаны в порядке их добавления
     *
     * @throws NullPointerException если speech==null
     */
    public void addSpeech(String... speech) {
        speechList.addAll(List.of(speech));
        //todo case when no arguments: sitcomActorObj.addSpeech();
    }

    public void exit() {
        try {
            connection.send(new Message(MessageType.USER_REMOVED));
        } catch (IOException e) {
            actorJoined = false;
            ConsoleHelper.writeMessage("Error, connection will be closed");
        }
        actorJoined = false;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public boolean isActorJoined() {
        return actorJoined;
    }

    //happens before: Вызов этого сеттера из класса TheScene будет после создания нового объекта SitcomActor
    //happens before: Вызов метода объектом SitcomActor obj будет после вызова из TheScene.playTheScene
    public void setMaySay(boolean maySay) {
        this.maySay = maySay;
    }
}
