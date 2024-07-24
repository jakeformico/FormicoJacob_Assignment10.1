package observer;
import java.io.*;
import java.util.Random;

public class zzUtil {

    public zzUtil() throws IOException {
    }

    public static void writeToFile(String message, int id) throws IOException {
        if(id > 0) {
            PrintWriter pp = new PrintWriter(new FileOutputStream(new File("willis_scott_out" + id + ".txt"), true));
            pp.append(message).append("\n");
            pp.close();
        }
        PrintWriter p = new PrintWriter(new FileOutputStream(new File("willis_scott_out.txt"), true));
        p.append(message).append("\n");
        p.close();

    }

    public static int getRandomInt(int range){
        return new Random().nextInt(range);
    }
}
