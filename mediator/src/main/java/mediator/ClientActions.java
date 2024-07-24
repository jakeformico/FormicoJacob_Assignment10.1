package mediator;

import java.util.Random;

public class ClientActions extends Thread {

    Mediator mediator;

    public void run() {
        Random r = new Random();

        // Create mediator
        Mediator mediator = new MessageMediator();
        this.mediator = mediator;
        mediator.start();

        // Create 10 colleague threads
        for (int x = 0; x < 10; x++) {
            
            Colleague colleague = new MessageColleague(x + 1, r.nextInt((3 - 1) + 1) + 1, mediator);
            mediator.addColleague(colleague);
            colleague.start();
        }

    }

    public void end() {
        mediator.end();
    }

}
