package cor;

public interface Handler {

    public void handleRequest(Request request); 

    public void addNextHandler(Handler nextHandler);

    public int getHandlerId();

    public void start();

    public void end();

}
