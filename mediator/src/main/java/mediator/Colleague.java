package mediator;

public interface Colleague {

    public void handleBroadcast(Request request, int sourceColleague);

    public void handleMessage(Request request, int sourceColleague);

    public void handleRandom(Request request, int sourceColleague);

    public void addMediator(Mediator mediator);

    public int getColleagueId();

    public int getColleagueMessageGroup();

    public void executeCommand();

    public void start();

    public void end();

}
