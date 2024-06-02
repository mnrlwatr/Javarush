package JavaRush.MiniProjects.chatting.Util;

import java.util.HashSet;
import java.util.Set;

public class ClientDAO {

    private static final Set<String> clients = new HashSet<>();

    private ClientDAO() {
        throw new RuntimeException("Это утилитарный класс");
    }

    public static boolean addNewClient (String name){
        return clients.add(name);
    }




}
