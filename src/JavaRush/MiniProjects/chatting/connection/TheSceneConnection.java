package JavaRush.MiniProjects.chatting.connection;

import java.io.IOException;
import java.net.Socket;

public class TheSceneConnection extends Connection {

    public TheSceneConnection() throws IOException {
        super(new Socket("localhost", 4004));
    }
}
