package observer;

public interface Observer {

    public void update(Subject broadcaster);


    public void start();
    public void end();

}
