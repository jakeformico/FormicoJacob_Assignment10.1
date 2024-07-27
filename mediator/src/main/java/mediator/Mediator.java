package mediator;

public interface Mediator {

    public void start();

    public void passRequest(Request request, int colleagueId);

    public void addColleague(Colleague colleague);

    public void end();

}
