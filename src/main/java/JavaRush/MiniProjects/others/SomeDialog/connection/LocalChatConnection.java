package JavaRush.MiniProjects.others.SomeDialog.connection;

import java.io.IOException;
import java.net.Socket;

public class LocalChatConnection extends Connection {

    public LocalChatConnection() throws IOException {
        super(new Socket("localhost", 4004));
    }
}
