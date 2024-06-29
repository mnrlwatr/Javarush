package JavaRush.tasks.Quest4.task3810;

public @interface Author {
    String value();

    Position position() default Position.OTHER;
}
