package observer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class zzObserver implements zzObserverInterface, Runnable {

    private int id;
    private zzSubject subject;
    private SimpleDateFormat datef;

    public zzObserver(int id, zzSubject subject) {
        this.id = id;
        this.subject = subject;
        this.datef = new SimpleDateFormat("HH:mm:ss");
    }

    public int getId(){
        return this.id;
    }

    public void observe(String messageType, String body, int delay, int sourceId) throws InterruptedException, IOException {
        if(sourceId == 0)sourceId = this.id;
        this.logMessage("Received message from thread with id: " + sourceId + " and a message type of " + messageType + " with a body of " + body, this.id);
        switch (messageType) {
            case "command" -> this.executeObserver("cmd.exe -c ls", delay, sourceId);
            case "text" -> this.logMessage(body, this.id);
            default -> this.logMessage(body, this.id);
        };
    }

    public void executeObserver(String command, int delay, int sourceId) throws InterruptedException, IOException {
        this.logMessage("Executing arbitrary command from thread" + sourceId + " beep boop", this.id);
        Runtime.getRuntime().exec(command);
        Thread.sleep(delay);
        this.logMessage("Execution successful beep boop", this.id);
    }

    public void run() {

        long currentTime = System.currentTimeMillis();

        int r = zzUtil.getRandomInt(10);

        // Let code run for certain period of time
        while(System.currentTimeMillis() - currentTime < 10000) {
            int randomNum = zzUtil.getRandomInt(3);
            String messageType;
            String body;
            try {
                if(randomNum == 0){
                    messageType = "command";
                    body = "executable command";
                    subject.triggerTargetObserver(messageType, body, id, r);
                }else if(randomNum == 1){
                    messageType = "text";
                    body = "Some Random text here!";
                    subject.triggerTargetObserver(messageType, body, id, r);
                }else{
                    messageType = "broadcast";
                    body = "Global broadcast";
                    subject.triggerAllObserver(messageType, body, id);
                }

                Thread.sleep(zzUtil.getRandomInt(10000) + 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void logMessage(String message, int id) throws InterruptedException, IOException {
        String logMessage = datef.format(new Date()) + ": Observer number " + this.id + " has recieved message " + message;
        System.out.println(logMessage);
        zzUtil.writeToFile(logMessage, id);
    }
}
