package observer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class Client {

    public static void main(String args[]) {

        Subject broadcaster = new Broadcaster();

        Random r = new Random();

        for (int x = 0; x < 10; x++) {
            
            Observer receiver = new Receiver(x + 1, r.nextInt((3 - 1) + 1) + 1);

            broadcaster.attach(receiver);
            receiver.start();
        }


        // Start thread
        broadcaster.start();

    
        try {
            // Run for 60 seconds
            TimeUnit.SECONDS.sleep(60);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        broadcaster.end();
        System.out.println("done");
        System.exit(0);

    }
}