package observer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Receiver extends Thread implements Observer {

    String message;
    Broadcaster broadcaster;
    int threadNumber;
    int messageGroup;
    FileWriter myWriter;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalTime timeNow;

    public Receiver(int threadNumber, int messageGroup) {
        this.threadNumber = threadNumber;
        this.messageGroup = messageGroup;
        try {
            myWriter = new FileWriter("observer" + threadNumber + ".txt");
            timeNow = LocalTime.now();
            myWriter.append("Observer" + threadNumber + " started. at time: " + dtf.format(timeNow)
                    + " in message group: " + messageGroup + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            File myObj = new File("observer" + threadNumber + ".txt");
            myObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {

        }

    }

    @Override
    public void update(Subject broadcaster) {

        // Do something based on the type of the subject
        message = broadcaster.getMessage();
        try {
            if (broadcaster.getMessageType().equals("BROADCAST")) {
                timeNow = LocalTime.now();
                myWriter.append("Got broadcast: " + message + " from " + broadcaster.toString() + " in mode: "
                        + broadcaster.getMessageType() + " at time: " + dtf.format(timeNow) + "\n");
            } else if (broadcaster.getMessageType().equals("MESSAGE")
                    && broadcaster.getMessageGroupNumber() == messageGroup) {
                timeNow = LocalTime.now();
                myWriter.append("Got message: " + message + " from " + broadcaster.toString() + " in mode: "
                        + broadcaster.getMessageType() + " at time: " + dtf.format(timeNow) + "\n");
            } else if (broadcaster.getMessageType().equals("COMMAND")) {
                doACommand();
            } else if (broadcaster.getMessageType().equals("RANDOM")
                    && broadcaster.getRandomObserverNumber() == threadNumber) {
                timeNow = LocalTime.now();
                myWriter.append("Was selected randomly: " + message + " from " + broadcaster.toString() + " in mode: "
                        + broadcaster.getMessageType() + " at time: " + dtf.format(timeNow) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void end() {
        try {
            myWriter.close();
        } catch (IOException e) {
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

}
