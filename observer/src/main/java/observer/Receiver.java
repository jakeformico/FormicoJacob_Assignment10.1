package observer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Receiver extends Thread implements Observer {

    String message;
    Broadcaster broadcaster;
    int threadNumber; // TODO: Maybe change this name----------------------------------------------
    int messageGroup; // TODO: Maybe change this name----------------------------------------------
    FileWriter writer; 
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalTime timeNow; // TODO: Maybe change this name----------------------------------------------

    public Receiver(int threadNumber, int messageGroup) {
        this.threadNumber = threadNumber;
        this.messageGroup = messageGroup;
        try {
            writer = new FileWriter("observer" + threadNumber + ".txt");
            timeNow = LocalTime.now();
            writer.append("Observer" + threadNumber + " started. at time: " + dateFormatter.format(timeNow)
                    + " in message group: " + messageGroup + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            File fileObj = new File("jf_observer-" + threadNumber + ".txt");
            fileObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {// TODO: What is this?----------------------------------------------

        }

    }

    @Override
    public void update(Subject broadcaster) {

        message = broadcaster.getMessage();
        try {// TODO: UPDATE CONDITIONAL ORDER BASED ON ENUM ORDER----------------------------------------------

            if (broadcaster.getMessageType().equals("BROADCAST")) {
                timeNow = LocalTime.now();
                writer.append("Recieved broadcast: " + message + " from " + broadcaster.toString() + " in mode: "
                        + broadcaster.getMessageType() + " at time: " + dateFormatter.format(timeNow) + "\n");
            } else if (broadcaster.getMessageType().equals("MESSAGE")
                    && broadcaster.getMessageGroupNumber() == messageGroup) {
                timeNow = LocalTime.now();
                writer.append("Recieved message: " + message + " from " + broadcaster.toString() + " in mode: "
                        + broadcaster.getMessageType() + " at time: " + dateFormatter.format(timeNow) + "\n");
            } else if (broadcaster.getMessageType().equals("COMMAND")) {
                doACommand();
            } else if (broadcaster.getMessageType().equals("RANDOM")
                    && broadcaster.getRandomObserverNumber() == threadNumber) {
                timeNow = LocalTime.now();
                writer.append("Was selected randomly: " + message + " from " + broadcaster.toString() + " in mode: "
                        + broadcaster.getMessageType() + " at time: " + dateFormatter.format(timeNow) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void end() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // TODO: UPDATE THIS METHOD CHANGE IT UP----------------------------------------------
    public void doACommand() { // TODO: MAKE UPDATES TO OTHER CLASSES AS WELL----------------------------------------------
        timeNow = LocalTime.now();
        try {
            writer.append("I performed some command at time: " + dateFormatter.format(timeNow) + "\n");// TODO: UPDATE THIS METHOD CHANGE IT UP----------------------------------------------
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
