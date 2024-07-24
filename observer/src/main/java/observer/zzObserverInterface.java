package observer;
import java.io.IOException;

public interface zzObserverInterface {

    void observe(String type,String body, int delay, int sourceId) throws InterruptedException, IOException;

    void executeObserver(String command, int delay, int sourceId) throws InterruptedException, IOException;

    void run();

    int getId();

    void logMessage(String message, int id) throws InterruptedException, IOException;
}
