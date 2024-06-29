package JavaRush.tasks.Quest3.task24.task2411;

public interface JustAnInterface {
    public static final B B = new B();

    class B extends C{
        public B() {
            System.out.print("B");
        }
    }
}