package JavaRush.tasks.Quest3.task27.task2710;

/* 
Расставьте wait-notify
*/
public class Solution {
    public static void main(String[] args) {
        Mail mail = new Mail();
        Thread server = new Thread(new MailServer(mail));
        Thread amigo = new Thread(new Person(mail));

        server.start();
        amigo.start();
    }
}
