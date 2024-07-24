package observer;
public interface Subject {

    public void attach(Observer observer);

    public void detach(Observer observer);

    public void Notify();

    public void start();

    public String getMessage();

    public String getMessageType();

    public void end();

    public int getMessageGroupNumber();

    public int getRandomObserverNumber();

}
