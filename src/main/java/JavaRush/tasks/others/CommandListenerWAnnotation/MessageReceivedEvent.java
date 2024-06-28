package JavaRush.tasks.others.CommandListenerWAnnotation;

public class MessageReceivedEvent {

    private String message;

    public MessageReceivedEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
