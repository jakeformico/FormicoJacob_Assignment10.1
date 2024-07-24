package mediator;

import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String args[]) {

        ClientRunner clientToRun = new ClientRunner();
        
        //starting thread
        clientToRun.start();

        // Run for a minute
        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clientToRun.end();
        System.out.println("ENDED!");

        System.exit(0);

    }

}
