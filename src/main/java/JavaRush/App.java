package JavaRush;

import lombok.Data;

/**
 * Hello world!
 *
 */
@Data
public class App {

    String name;
    int age =45;
    public static void main( String[] args )
    {
        System.out.println(new App().getAge());
    }
}
