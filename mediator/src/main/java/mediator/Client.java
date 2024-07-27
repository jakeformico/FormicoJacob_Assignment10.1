package mediator;

import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String args[]) {

        ClientRunner runner = new ClientRunner();
        
        // Start thread
        runner.start();

        
        try {
            // Run for 60 seconds
            TimeUnit.SECONDS.sleep(60);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runner.end();
        System.out.println("done");
        System.exit(0);

    }

}
