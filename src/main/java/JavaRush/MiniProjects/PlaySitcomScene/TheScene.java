package JavaRush.MiniProjects.PlaySitcomScene;

import JavaRush.MiniProjects.PlaySitcomScene.Util.ConsoleHelper;
import JavaRush.MiniProjects.PlaySitcomScene.connection.Connection;
import JavaRush.MiniProjects.PlaySitcomScene.connection.Message;
import JavaRush.MiniProjects.PlaySitcomScene.connection.MessageType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


public class TheScene {

    private static final Map<String, Connection> connectionMap = new ConcurrentHashMap<>();
    private static final Semaphore semaphore = new Semaphore(1);
    private static volatile int consoleOutTime;

    private TheScene() {
        throw new RuntimeException();
    }

    private static class Handler extends Thread {

        private final Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            String actorName = null;
            try (Connection connection = new Connection(socket)) {
                actorName = handShake(connection);
                informJoinedActor(actorName);
                mainLoop(connection, actorName);
                informLeftActor(actorName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Error!");
            }

            if (actorName != null) {
                connectionMap.remove(actorName);
            }

        }

        /**
         * @param connection
         * @return Имя нового актёра
         * @throws IOException
         * @throws ClassNotFoundException
         */
        private String handShake(Connection connection) throws IOException, ClassNotFoundException {
            String name;
            // Если какая-то проверка не прошла, заново запросить имя клиента
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() == MessageType.USER_NAME) {
                    name = message.getData();
                    if (!name.isEmpty() && !connectionMap.containsKey(name)) {
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        break;
                    }
                }
            }

            return name;
        }

        /**
         * Цикл обработки сообщений
         *
         * @param connection
         * @param userName
         * @throws IOException
         * @throws ClassNotFoundException
         */
        private void mainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {

            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    String messageText = userName + ": " + message.getData();
                    timedConsoleOut(messageText);
                } else if (message.getType() == MessageType.USER_REMOVED) {
                    break;
                }
            }

        }

        private void timedConsoleOut(String speech) {
            try {
                TimeUnit.SECONDS.sleep(consoleOutTime);
                ConsoleHelper.writeMessage(speech);
                semaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }


        private void informJoinedActor(String actorName) {
            ConsoleHelper.writeMessage(actorName + " joined");
        }

        private void informLeftActor(String actorName) {
            ConsoleHelper.writeMessage(actorName + "  left");
        }
    }


    /**
     * Актёры по очереди говорят свою речь с интервалом time
     * Одна речь за один роль
     *
     * @param roleQueue Роли будут сыграны в порядке их добавление в roleQueue
     * @param time      Время в секундах
     */
    public static void playTheScene(LinkedList<SitcomActor> roleQueue, int time) {
        consoleOutTime = time;
        methodName1();
        new Thread(() -> play(roleQueue)).start();
    }

    private static void methodName1 (){
        try (ServerSocket serverSocket = new ServerSocket(4004)) {
            while (true) {
                serverSocket.setSoTimeout(3000);
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (IOException ignored) {
        }
    }

    private static void play(LinkedList<SitcomActor> roleQueue) {
        for (SitcomActor actor : roleQueue) {
            // если какой-то актёр выйдет до прихода его очереди, то программа зависнет в semaphore.acquire
            // проверяем не вышел ли актёр до semaphore.acquire
            if(actor.isActorJoined()){
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
                actor.setMaySay(true);
            }

        }
    }

}
