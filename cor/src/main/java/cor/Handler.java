package cor;

public interface Handler {

    public void handleRequest(Request request); 

    public void addSuccessor(Handler successor);

    public int getHandlerId(); // Update method name ? TBD

    public void start();

    public void end();

}
