package JavaRush.MiniProjects.PlaySitcomScene.connection;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Connection implements Closeable{
    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        // Создания объекта класса ObjectOutputStream до того, как будет создаваться объект
        // класса ObjectInputStream, иначе может возникнуть взаимная блокировка потоков,
        // которые хотят установить соединение
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    public void send(Message message) throws IOException {
        synchronized (out){
            out.writeObject(message);
        }
    }

    public Message receive() throws IOException, ClassNotFoundException {
        synchronized (in){
            return (Message)in.readObject();
        }
    }

    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
