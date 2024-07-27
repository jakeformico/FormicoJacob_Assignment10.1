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
    DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalTime currentTime;
    Handler nexthandler;

    public MessageHandler(int threadNumber, int messageGroup) {
        this.threadNumber = threadNumber;
        this.messageGroup = messageGroup;
        try {
            myWriter = new FileWriter("jf_handler-" + threadNumber + ".txt");
            currentTime = LocalTime.now();
            myWriter.append("Handler " + threadNumber + " at " + dateformat.format(currentTime)
                    + " in group: " + messageGroup + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            File handlerFile = new File("jf_handler-" + threadNumber + ".txt");
            handlerFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {

        }

    }

    @Override
    public void addNextHandler(Handler nexthandler) {
        this.nexthandler = nexthandler;
    }

    @Override
    public void handleRequest(Request request) {

        boolean bypass = false;

        message = request.getMessage();
        try {
            // Broadcast case
            if (request.getMessageType().equals("BROADCAST")) {
                currentTime = LocalTime.now();
                myWriter.append("Recieved broadcast: " + message + " in mode: "
                        + request.getMessageType() + " at time: " + dateformat.format(currentTime) + "\n");
                // Message case
            } else if (request.getMessageType().equals("MESSAGE")
                    && request.getMessageGroupNumber() == messageGroup) {
                currentTime = LocalTime.now();
                myWriter.append("Recieved message: " + message + " from " + request.toString() + " in mode: "
                        + request.getMessageType() + " at time: " + dateformat.format(currentTime) + "\n");
                // Command Case
            } else if (request.getMessageType().equals("COMMAND")) {
                currentTime = LocalTime.now();
                executeCommand();
            } else if (request.getMessageType().equals("RANDOM")
                    && request.getRandomObserverNumber() == threadNumber) {
                currentTime = LocalTime.now();
                myWriter.append("Randomly recieved: " + message + " from " + request.toString() + " in mode: "
                        + request.getMessageType() + " at time: " + dateformat.format(currentTime) + "\n");
                bypass = true;
            }
            // Pass message
            if (nexthandler != null && bypass != true) {
                myWriter.append("Pass to: " + nexthandler.getHandlerId() + " in mode: "
                        + request.getMessageType() + " at: " + dateformat.format(currentTime) + "\n");
                nexthandler.handleRequest(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void end() {
        try {
            myWriter.close();
            if (nexthandler != null) {
                nexthandler.end();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void executeCommand() {
        currentTime = LocalTime.now();
        try {
            myWriter.append("Do command xyz.. : " + dateformat.format(currentTime) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getHandlerId() {
        return this.threadNumber;
    }

}
