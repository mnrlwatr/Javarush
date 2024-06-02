package JavaRush.MiniProjects.FactoryMethodPattern.male;

import JavaRush.MiniProjects.FactoryMethodPattern.AbstractFactory;
import JavaRush.MiniProjects.FactoryMethodPattern.Human;

public class MaleFactory implements AbstractFactory {
    @Override
    public Human getPerson (int age){
        Human human=null;
       if(age<=KidBoy.MAX_AGE){
            human=new KidBoy();
       } else if (age<=TeenBoy.MAX_AGE) {
            human=new TeenBoy();
       } else {
           human=new Man();
       }
       return human;
    }
}
