package JavaRush.MiniProjects.PlaySitcomScene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        SitcomActor Chandler = new SitcomActor("Chandler");
        Chandler.addSpeech("Hey.",
                "And this from the cry-for-help department. Are you wearing makeup?",
                "That`s so funny, `cause i am thinking you look more like Joey Tribbiani, man slash woman.",
                "Do you know which one you are gonna be?",
                "Good luck, man. I hope you get it.");

        SitcomActor Joey = new SitcomActor("Joey");
        Joey.addSpeech("Hey, hey.",
                "Yes, I am. As of today, i am officially Joey Tribbiani, actor slash model.",
                "You know those posters for the City Free Clinic?",
                "No, but I hear lyme disease is open, so... (crosses fingers)",
                "Thanks.");

        SitcomActor Monica = new SitcomActor("Monica");
        Monica.addSpeech("oh, wow, so you`re gonna be one of those \"healthy, healthy, healthy guys\"?");

        SitcomActor Phoebe = new SitcomActor("Phoebe");
        Phoebe.addSpeech("Hey.", "What were you modeling for?", "You know, the asthma guy was really cute.");

        SitcomActor Ross = new SitcomActor("Ross");

        SitcomActor Rachel = new SitcomActor("Rachel");

        List<SitcomActor> actors = new ArrayList<>();
        Collections.addAll(actors, Chandler, Joey, Monica, Phoebe, Ross, Rachel);

        for (SitcomActor actor : actors) {
            new Thread(actor).start();
        }


        LinkedList<SitcomActor> rolesQueue = new LinkedList<>();
        rolesQueue.add(Joey);
        rolesQueue.add(Chandler);
        rolesQueue.add(Phoebe);
        rolesQueue.add(Chandler);
        rolesQueue.add(Joey);
        rolesQueue.add(Chandler);
        rolesQueue.add(Phoebe);
        rolesQueue.add(Joey);
        rolesQueue.add(Monica);
        rolesQueue.add(Phoebe);
        rolesQueue.add(Chandler);
        rolesQueue.add(Joey);
        rolesQueue.add(Chandler);
        rolesQueue.add(Joey);

        TheScene.playTheScene(rolesQueue, 2);


        // если убрать sleep(35) то actors.forEach(SitcomActor::exit) сразу прервёт сценку
        // можете поставить sleep(20) и посмотреть на результат (сценка прервётся, актёры не успеют сказать всю речь)
        try {
            TimeUnit.SECONDS.sleep(35);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        actors.forEach(SitcomActor::exit);

    }
}
