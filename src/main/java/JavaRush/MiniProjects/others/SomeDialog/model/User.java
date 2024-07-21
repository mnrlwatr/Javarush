package JavaRush.MiniProjects.others.SomeDialog.model;

import JavaRush.MiniProjects.others.SomeDialog.LocalChatJoinable;
import JavaRush.MiniProjects.others.SomeDialog.service.LocalChatJoiner;
import JavaRush.MiniProjects.others.SomeDialog.service.UserNameChecker;
import java.util.LinkedList;
import java.util.List;

public class User implements LocalChatJoinable {

    private final String name;
    private final LinkedList<String> speechList;
    private LocalChatJoiner localChatJoiner;

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
    public void joinToLocalChat() {
        localChatJoiner = new LocalChatJoiner(this);
    }


    /**
     * @throws NullPointerException если speech==null
     */
    public void addSpeech(String... speech) {
        speechList.addAll(List.of(speech));
        //todo case when no arguments: sitcomActorObj.addSpeech();
    }

    public void clearSpeechList() {
        speechList.clear();
    }

    @Override
    public void exitFromLocalChat() {
        localChatJoiner.exit();
    }

    // Getters and Setters

    public LocalChatJoiner getLocalChatJoiner() {
        return localChatJoiner;
    }

    public String getName() {
        return name;
    }

    public LinkedList<String> getSpeechList() {
        return speechList;
    }


}
