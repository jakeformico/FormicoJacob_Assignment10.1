package mediator;

public interface Mediator {

    public void start();

    public void passRequest(Request request, int colleagueID);

    public void addColleague(Colleague colleague);

    public void end();

}
