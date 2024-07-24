package cor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MessageHandler extends Thread implements Handler {

    String message;
    int threadNumber;
    int messageGroup;
    FileWriter myWriter;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalTime timeNow;
    Handler successor;

    public MessageHandler(int threadNumber, int messageGroup) {
        this.threadNumber = threadNumber;
        this.messageGroup = messageGroup;
        try {
            myWriter = new FileWriter("handler" + threadNumber + ".txt");
            timeNow = LocalTime.now();
            myWriter.append("Handler" + threadNumber + " at " + dtf.format(timeNow)
                    + " in message group: " + messageGroup + "\n"); // TODO: THIS IS FOR THE FILE HEADER MAYBE UPDATE
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            File myObj = new File("handler" + threadNumber + ".txt");
            myObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {

        }

    }

    @Override
    public void addSuccessor(Handler successor) {
        this.successor = successor;
    }

    @Override
    public void handleRequest(Request request) {

        boolean bypass = false;

        message = request.getMessage();
        try {
            // Broadcast case
            if (request.getMessageState().equals("BROADCAST")) {
                timeNow = LocalTime.now();
                myWriter.append("Recieved broadcast: " + message + " in mode: "
                        + request.getMessageState() + " at time: " + dtf.format(timeNow) + "\n");
                // Message case
            } else if (request.getMessageState().equals("MESSAGE")
                    && request.getMessageGroupNumber() == messageGroup) {
                timeNow = LocalTime.now();
                myWriter.append("Recieved message: " + message + " from " + request.toString() + " in mode: "
                        + request.getMessageState() + " at time: " + dtf.format(timeNow) + "\n");
                // Command Case
            } else if (request.getMessageState().equals("COMMAND")) {
                timeNow = LocalTime.now();
                doACommand();
            } else if (request.getMessageState().equals("RANDOM")
                    && request.getRandomObserverNumber() == threadNumber) {
                timeNow = LocalTime.now();
                myWriter.append("Randomly recieved: " + message + " from " + request.toString() + " in mode: "
                        + request.getMessageState() + " at time: " + dtf.format(timeNow) + "\n");
                bypass = true;
            }
            // Pass message
            if (successor != null && bypass != true) {
                myWriter.append("Pass to: " + successor.getHandlerId() + " in mode: "
                        + request.getMessageState() + " at: " + dtf.format(timeNow) + "\n");
                successor.handleRequest(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void end() {
        try {
            myWriter.close();
            if (successor != null) {
                successor.end();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void doACommand() {
        timeNow = LocalTime.now();
        try {
            myWriter.append("I performed some command at time: " + dtf.format(timeNow) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getHandlerId() {
        return this.threadNumber;
    }

}
