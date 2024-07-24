package observer;


import java.io.FileWriter;
import java.io.PrintWriter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class zzMain {
    public static void main(String[] args) {

        // Create filewrite and pass to broadcaster
        // Has to be done in two seperate steps otherwise threads fail at 3
        zzSubject subject = new zzSubject();

            //Create all 10 broadcasters starting with 1 and start threads
        for (int i = 1; i <= 10; i++) {

            zzObserver o = new zzObserver(i, subject); //passing in the subject object for each observer
            subject.addObserver(o);
            new Thread(o).start();
        }

    }
}