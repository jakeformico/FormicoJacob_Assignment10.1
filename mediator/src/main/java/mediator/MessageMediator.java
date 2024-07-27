package mediator;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class MessageMediator extends Thread implements Mediator {

    FileWriter myWriter;
    DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalTime currentTime;

    HashMap<Integer, Colleague> colleagues = new HashMap<Integer, Colleague>();

    public MessageMediator() {

        try {
            myWriter = new FileWriter("mediator.txt");
            currentTime = LocalTime.now();
            myWriter.append("Mediator started. at time: " + dateformat.format(currentTime) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addColleague(Colleague colleague) {
        this.colleagues.put(Integer.valueOf(colleague.getColleagueId()), colleague);
    }

    @Override
    public synchronized void passRequest(Request request, int colleagueId) {

        int sourceColleague = colleagueId;

        currentTime = LocalTime.now();
        try {
            myWriter.append("Recieved request type: " + request.getMessageType() + " from colleague: "
                    + colleagueId + " at time: " + dateformat.format(currentTime) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Broadcast case
        if (request.getMessageType().equals("BROADCAST")) {
            for (int x = 1; x < 11; x++) {
                colleagues.get(x).handleBroadcast(request, sourceColleague);
            }
        }
        // Message case
        else if (request.getMessageType().equals("MESSAGE")) {
            for (int x = 1; x < 11; x++) {
                if (colleagues.get(x).getColleagueMessageGroup() == request.messageGroupNumber) {
                    colleagues.get(x).handleMessage(request, sourceColleague);
                }
            }
        }
        // Command case
        else if (request.getMessageType().equals("COMMAND")) {
            for (int x = 1; x < 11; x++) {
                colleagues.get(x).executeCommand();
            }
        }
        // Single case
        else if (request.getMessageType().equals("RANDOM")) {
            colleagues.get(request.randomColleagueNumber).handleRandom(request, sourceColleague);
        }

    }

    @Override
    public void run() {

    }

    @Override
    public void end() {
        for (int x = 1; x < 11; x++) {
            colleagues.get(x).end();
        }
        try {
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
