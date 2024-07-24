package cor;
import java.util.Random;

public class Request {
    String message = "REQUESTING....";
    MessageState messageState;
    int messageGroupNumber = 1;
    String messageGroupString = "";
    Handler nextInChain;
    int randomObserverNumber;
    String randomMessage = "";

    public String getMessage() {
        return message;
    }

    public String getMessageState() {
        return this.messageState.toString();
    }

    public int getMessageGroupNumber() {
        return this.messageGroupNumber;
    }

    public void randomMessageState() {
        this.messageState = MessageState.values()[new Random().nextInt(MessageState.values().length)];
    }

    public void randomMessageGroupNumber() {
        Random r = new Random();
        this.messageGroupNumber = r.nextInt((3 - 1) + 1) + 1;
    }

    private int selectRandomObserver() {
        Random r = new Random();
        return this.messageGroupNumber = r.nextInt((10 - 1) + 1);
    }

    public int getRandomObserverNumber() {
        return this.randomObserverNumber;
    }

    public Request getNewRequest() {

        randomMessageState();
        randomMessageGroupNumber();
        if (this.getMessageState().equals("MESSAGE")) {
            this.messageGroupString = "message group: " + messageGroupNumber;
        } else if (this.getMessageState().equals("RANDOM")) {
            randomObserverNumber = selectRandomObserver();
            randomMessage = "random observer selected: " + randomObserverNumber;
        }

        return this;
    }

}
