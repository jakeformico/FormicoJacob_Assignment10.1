package cor;
import java.util.Random;

public class Request {
    String message = "REQUESTING....";
    MessageType messageType;
    int messageGroupNumber = 1;
    String messageGroupString = "";
    Handler nextInChain;
    int randomObserverNumber;
    String randomMessage = "";

    public String getMessage() {
        return message;
    }

    public String getMessageType() {
        return this.messageType.toString();
    }

    public int getMessageGroupNumber() {
        return this.messageGroupNumber;
    }

    public void randomMessageType() {
        this.messageType = MessageType.values()[new Random().nextInt(MessageType.values().length)];
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

        randomMessageType();
        randomMessageGroupNumber();
        if (this.getMessageType().equals("MESSAGE")) {
            this.messageGroupString = "message group: " + messageGroupNumber;
        } else if (this.getMessageType().equals("RANDOM")) {
            randomObserverNumber = selectRandomObserver();
            randomMessage = "random observer selected: " + randomObserverNumber;
        }

        return this;
    }

}
