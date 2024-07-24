package mediator;

import java.util.Random;

public class Request {
    String message = "Don't do drugs! Or do!";
    MessageType messageState;
    int messageGroupNumber = 1;
    String messageGroupString = "";
    Colleague nextInChain;
    int randomColleagueNumber;
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
        this.messageState = MessageType.values()[new Random().nextInt(MessageType.values().length)];
    }

    public void randomMessageGroupNumber() {
        Random r = new Random();
        this.messageGroupNumber = r.nextInt((3 - 1) + 1) + 1;
    }

    private int selectRandomObserver() {
        Random r = new Random();
        return this.messageGroupNumber = r.nextInt((10 - 1) + 1);
    }

    public int getRandomColleagueNumber() {
        return this.randomColleagueNumber;
    }

    public Request getNewRequest() {

        randomMessageState();
        randomMessageGroupNumber();
        this.randomColleagueNumber = selectRandomObserver();
        if (this.getMessageState().equals("MESSAGE")) {
            this.messageGroupString = "message group: " + messageGroupNumber;
        } else if (this.getMessageState().equals("RANDOM")) {
            randomMessage = "random observer selected: " + randomColleagueNumber;
        }

        return this;
    }

}
