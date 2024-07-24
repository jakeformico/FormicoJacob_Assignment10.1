package cor;

import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String args[]) {

        ClientRunner clientToRun = new ClientRunner();
        clientToRun.start();

        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clientToRun.end();
        // System.out.println("Ended Run");

        System.exit(0);

    }

}
