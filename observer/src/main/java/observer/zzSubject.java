package observer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class zzSubject implements zzSubjectInterface {

    private List<zzObserver> observers;
    private SimpleDateFormat datef;

    // Will keep track of all the observers
    public zzSubject() {
        this.observers = new ArrayList<zzObserver>();
        this.datef = new SimpleDateFormat("HH:mm:ss");
    }

    public synchronized void addObserver(zzObserver observer) {
        this.observers.add(observer);
    }

    public synchronized void triggerObserver(String messageType, String body) throws InterruptedException, IOException {
        logMessage("Logging message: " + messageType + " with body " + body);
        for (zzObserver o: this.observers){
            o.observe(messageType, body, zzUtil.getRandomInt(1000), 0);
        }
    }

    public void logMessage(String message) throws IOException {
        String logMessage = datef.format(new Date()) + message;
        System.out.println(logMessage);
        zzUtil.writeToFile(logMessage, 0);
    }

    public synchronized void triggerTargetObserver(String messageType, String body, int sourceId, int targetId) throws IOException, InterruptedException {
        for (zzObserver o: this.observers){
            if(o.getId() == targetId){
                o.observe(messageType, body, zzUtil.getRandomInt(1000), sourceId);
            }
        }
    }

    public synchronized void triggerAllObserver(String messageType, String body, int sourceId) throws IOException, InterruptedException {
        for (zzObserver o: this.observers){
            o.observe(messageType, body, zzUtil.getRandomInt(1000), sourceId);
        }
    }
}
