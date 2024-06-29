package JavaRush.MiniProjects.FactoryMethodPattern;

import JavaRush.MiniProjects.FactoryMethodPattern.female.FemaleFactory;
import JavaRush.MiniProjects.FactoryMethodPattern.male.MaleFactory;

public class FactoryProducer {
    public static AbstractFactory getFactory  (HumanFactoryType hft) {
        if(hft==null){
            throw new NullPointerException();
        }
        if(hft==HumanFactoryType.MALE){
            return new MaleFactory();
        } else if (hft==HumanFactoryType.FEMALE) {
            return new FemaleFactory();
        }
        return null;
    }
    public enum HumanFactoryType{
        MALE,
        FEMALE

    }
}
