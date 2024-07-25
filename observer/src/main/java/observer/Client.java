package observer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class Client {

    public static void main(String args[]) {

        Subject broadcaster = new Broadcaster();

        Random r = new Random();

        for (int x = 0; x < 10; x++) { // TODO: VERIFY THE ITERATION COUNT IS CORRECT
            
            Observer receiver = new Receiver(x + 1, r.nextInt((3 - 1) + 1) + 1);

            broadcaster.attach(receiver);
            receiver.start();
        }


        broadcaster.start();

    
        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        broadcaster.end();
        //System.out.println("ENDED!");

        System.exit(0);

    }
}