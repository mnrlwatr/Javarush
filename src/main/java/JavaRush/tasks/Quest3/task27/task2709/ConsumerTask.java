package JavaRush.tasks.Quest3.task27.task2709;

public class ConsumerTask implements Runnable {
    private TransferObject transferObject;
    protected volatile boolean stopped;

    public ConsumerTask(TransferObject transferObject) {
        this.transferObject = transferObject;
        new Thread(this, "ConsumerTask").start();
    }

    public void run() {
        while (!stopped) {
            synchronized (transferObject){
                transferObject.get();
            }
        }

    }

    public void stop() {
        stopped = true;
    }
}