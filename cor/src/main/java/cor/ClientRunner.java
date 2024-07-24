package cor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ClientRunner extends Thread {

    Handler baseHandler;

    public void run() {
        FileWriter myWriter;
        Random r = new Random();
        Handler baseHandler;
        Handler previousHandler;
        List<Integer> threadList = new ArrayList<Integer>();
        Collections.addAll(threadList, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int threadNumber;

        // Shuffle order of threads to chain
        Collections.shuffle(threadList);

        // Start first handler in chain
        baseHandler = new MessageHandler(1, r.nextInt((3 - 1) + 1) + 1);
        this.baseHandler = baseHandler;
        previousHandler = baseHandler;

        // Create thread chain
        for (int x = 0; x < 9; x++) {

            threadNumber = threadList.get(x);

            Handler handler = new MessageHandler(threadNumber, r.nextInt((3 - 1) + 1) + 1);
            previousHandler.addSuccessor(handler);
            previousHandler.start();
            previousHandler = handler;
        }

        // Print chain order to a file
        try {
            File myObj = new File("chainOrder.txt");
            myObj.createNewFile();
            myWriter = new FileWriter("chainOrder.txt");
            myWriter.append("Chain starts with [1] followed by: " + threadList.toString() + "\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Wait 2 seconds so all threads can properly start
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Send messages while thread is active
        while (true) {

            Request newRequest = new Request();
            newRequest = newRequest.getNewRequest();
            baseHandler.handleRequest(newRequest);
            try {
                sleep(r.nextInt((20000 - 1000) + 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void end() {
        baseHandler.end();
    }

}
