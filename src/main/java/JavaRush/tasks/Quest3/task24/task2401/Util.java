package JavaRush.tasks.Quest3.task24.task2401;

import java.lang.reflect.Method;

public class Util {
    //пример того, как можно использовать интерфейс-маркер
    //this method is available only for a SelfInterfaceMarker implementation
    public static void testClass(SelfInterfaceMarker interfaceMarker) throws UnsupportedInterfaceMarkerException {
        if (interfaceMarker != null){
            for (Method method : interfaceMarker.getClass().getDeclaredMethods()) {
                System.out.println(method);
            }
        }
        else {
            throw new UnsupportedInterfaceMarkerException();
        }


    }

}
