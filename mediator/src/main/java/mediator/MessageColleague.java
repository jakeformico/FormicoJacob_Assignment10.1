package mediator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class MessageColleague extends Thread implements Colleague {

    int threadNumber;
    int messageGroup;
    FileWriter writer;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalTime currentTime;
    Mediator mediator;
    Random r = new Random();

    public MessageColleague(int threadNumber, int messageGroup, Mediator mediator) {
        this.threadNumber = threadNumber;
        this.messageGroup = messageGroup;
        this.mediator = mediator;
        try {
            writer = new FileWriter("jf_colleague-" + threadNumber + ".txt");
            currentTime = LocalTime.now();
            writer.append("Colleague" + threadNumber + " started. at time: " + dateFormatter.format(currentTime)
                    + " in message group: " + messageGroup + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            File myObj = new File("jf_colleague-" + threadNumber + ".txt");
            myObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {

            try {
                sleep(r.nextInt((20000 - 1000) + 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Send messages while thread is active
            Request newRequest = new Request();
            newRequest = newRequest.getNewRequest();
            mediator.passRequest(newRequest, threadNumber);

        }

    }

    @Override
    public void addMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void handleMessage(Request request, int sourceColleague) {

        try {
            currentTime = LocalTime.now();
            writer.append("Recieved message: " + request.getMessage() + " from colleague: " + sourceColleague + " in mode: "
                    + request.getMessageType() + " at time: " + dateFormatter.format(currentTime) + "\n");
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
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void executeCommand() {
        currentTime = LocalTime.now();
        try {
            writer.append("Do command xyz... : " + dateFormatter.format(currentTime) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getColleagueId() {
        return this.threadNumber;
    }

    public int getColleagueMessageGroup() {
        return this.threadNumber;
    }

    @Override
    public void handleBroadcast(Request request, int sourceColleague) {

        currentTime = LocalTime.now();
        try {
            writer.append("Recieved broadcast: " + request.getMessage() + " from colleague: " + sourceColleague
                    + " in mode: " + request.getMessageType()
                    + " at time: " + dateFormatter.format(currentTime) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleRandom(Request request, int sourceColleague) {

        currentTime = LocalTime.now();
        try {
            writer.append("Was selected randomly: " + request.getMessage() + " from colleague: " + sourceColleague
                    + " in mode: "
                    + request.getMessageType() + " at time: " + dateFormatter.format(currentTime) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
