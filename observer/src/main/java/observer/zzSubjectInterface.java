package observer;
import java.io.IOException;

public interface zzSubjectInterface {
    void addObserver(zzObserver observer);

    void triggerObserver(String messageType, String body) throws InterruptedException, IOException;

    void logMessage(String message) throws IOException;

    void triggerTargetObserver(String messageType, String body, int sourceId, int targetId) throws IOException, InterruptedException;

    void triggerAllObserver(String messageType, String body, int sourceId) throws IOException, InterruptedException;
}
