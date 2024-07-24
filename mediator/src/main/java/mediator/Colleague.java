package mediator;

public interface Colleague {

    public void handleBroadcast(Request request, int fromColleague);

    public void handleMessage(Request request, int fromColleague);

    public void handleRandom(Request request, int fromColleague);

    public void addMediator(Mediator mediator);

    public int getColleagueId();

    public int getColleagueMessageGroup();

    public void doACommand();

    public void start();

    public void end();

}
