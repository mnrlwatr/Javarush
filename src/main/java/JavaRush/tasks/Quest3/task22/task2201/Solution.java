package JavaRush.tasks.Quest3.task22.task2201;

/*
Строки нитей или строковые нити? Вот в чем вопрос

1. Метод getPartOfString должен возвращать подстроку между первой и последней табуляцией.
2. На некорректные данные getPartOfString должен бросить исключение:
а) TooShortStringFirstThreadException, если имя трэда FIRST_THREAD_NAME.
б) TooShortStringSecondThreadException, если имя трэда SECOND_THREAD_NAME.
в) RuntimeException в других случаях.
3. Реализуйте логику трех protected методов в ThisUncaughtExceptionHandler используя вызовы соответствующих методов согласно следующим шаблонам:
a) 1# : TooShortStringFirstThreadException : java.lang.StringIndexOutOfBoundsException: String index out of range: -1
б) java.lang.StringIndexOutOfBoundsException: String index out of range: -1 : TooShortStringSecondThreadException : 2#
в) RuntimeException : java.lang.StringIndexOutOfBoundsException: String index out of range: -1 : 3#


Требования:
1. Метод getPartOfString должен возвращать подстроку между первой и последней табуляцией строки string переданной ему в качестве первого параметра.
2. В случае некорректных данных метод getPartOfString должен бросить исключение TooShortStringFirstThreadException, если имя трэда(threadName) Solution.FIRST_THREAD_NAME.
3. В случае некорректных данных метод getPartOfString должен бросить исключение TooShortStringSecondThreadException, если имя трэда(threadName) Solution.SECOND_THREAD_NAME.
4. В случае некорректных данных метод getPartOfString должен бросить исключение RuntimeException, если имя трэда(threadName) не Solution.FIRST_THREAD_NAME или Solution.SECOND_THREAD_NAME.
5. Метод getFormattedStringForFirstThread должен возвращать строку сформированную из переданных параметров по шаблону указанному в задании.
6. Метод getFormattedStringForSecondThread должен возвращать строку сформированную из переданных параметров по шаблону указанному в задании.
7. Метод getFormattedStringForOtherThread должен возвращать строку сформированную из переданных параметров по шаблону указанному в задании.
*/
public class Solution {
    public static void main(String[] args) {
        new Solution();
    }

    public static final String FIRST_THREAD_NAME = "1#";
    public static final String SECOND_THREAD_NAME = "2#";

    private Thread thread1;
    private Thread thread2;
    private Thread thread3;

    public Solution() {
        initThreads();
    }

    protected void initThreads() {
        this.thread1 = new Thread(new Task(this, "A\tB\tC\tD\tE\tF\tG\tH\tI"), FIRST_THREAD_NAME);
        this.thread2 = new Thread(new Task(this, "J\tK\tL\tM\tN\tO\tP\tQ\tR\tS\tT\tU\tV\tW\tX\tY\tZ"), SECOND_THREAD_NAME);
        this.thread3 = new Thread(new Task(this, "\t\t"), "3#");

        Thread.setDefaultUncaughtExceptionHandler(new ThisUncaughtExceptionHandler());

        this.thread1.start();
        this.thread2.start();
        this.thread3.start();
    }

    public synchronized String getPartOfString(String string, String threadName){
        //находим индекс первого вхождения
        int begin = string.indexOf('\t');
        //находим индекс последнего вхождения (первого с конца)
        int end = string.lastIndexOf('\t');
        //если ничего не найдено или первое вхождение == последнему
        if (begin == -1 || end == -1 || begin == end)
            //выкидываем исключение согласно условию
            switch (threadName) {
                case "1#": throw new TooShortStringFirstThreadException(new StringIndexOutOfBoundsException(-1));
                case "2#": throw new TooShortStringSecondThreadException(new StringIndexOutOfBoundsException(-1));
                default: throw new RuntimeException(new StringIndexOutOfBoundsException(-1));
            }

        //обрезаем строку по условию
        return string.substring(begin + 1, end);
    }
}
