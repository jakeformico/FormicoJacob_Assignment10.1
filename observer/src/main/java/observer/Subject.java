package observer;
public interface Subject {

    public void attach(Observer recievers);

    public void detach(Observer recievers);

    public void notifyRecievers();
    
    
    //TODO: VERIFY NAMES----------------------------
    public void start(); //TODO: VERIFY NAMES----------------------------

    public String getMessage();

    public String getMessageType();

    public void end();

    public int getMessageGroupNumber();

    public int getRandomObserverNumber();
    //TODO: VERIFY NAMES----------------------------

}
