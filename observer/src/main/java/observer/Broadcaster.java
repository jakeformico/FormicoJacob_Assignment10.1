// package observer;

// import java.io.File;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.time.LocalTime;
// import java.time.format.DateTimeFormatter;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Random;

// public class Broadcaster extends Thread implements Subject {

//     List<Observer> observers = new ArrayList<Observer>();
//     String message = "Don't do drugs! Or do!";
//     MessageState messageState;
//     FileWriter myWriter;
//     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
//     LocalTime timeNow;
//     int messageGroupNumber = 1;
//     String messageGroupString = "";
//     int randomObserverNumber;
//     String randomMessage = "";

//     @Override
//     public void attach(Observer newObserver) {
//         observers.add(newObserver);
//     }

//     @Override
//     public void detach(Observer observer) {

//         observers.remove(observer);
//     }

//     @Override
//     public void Notify() {

//         for (Observer observer : observers) {
//             observer.update(this);
//         }

//     }

//     @Override
//     public void run() {

//         try {
//             File myObj = new File("subject.txt");
//             myObj.createNewFile();
//             myWriter = new FileWriter("subject.txt");
//             timeNow = LocalTime.now();
//             myWriter.append("Broadcaster started. at time: " + dtf.format(timeNow) + "\n");
//         } catch (IOException e) {
//             e.printStackTrace();
//         }

//         while (true) {

//             randomMessageState();
//             randomMessageGroupNumber();
//             if (this.getMessageState().equals("MESSAGE")) {
//                 this.messageGroupString = "message group: " + messageGroupNumber;
//             } else if (this.getMessageState().equals("RANDOM")) {
//                 randomObserverNumber = selectRandomObserver();
//                 randomMessage = "random observer selected: " + randomObserverNumber;
//             }
//             try {
//                 timeNow = LocalTime.now();
//                 myWriter.append("Broadcaster sent message: " + message + " in state: " + messageState + " at time: "
//                         + dtf.format(timeNow) + " " + messageGroupString + randomMessage + "\n");
//                 messageGroupString = "";
//                 randomMessage = "";
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }

//             setMessage("Don't do drugs! Or do!");
//             Notify();
//             try {
//                 Random r = new Random();
//                 sleep(r.nextInt((20000 - 1000) + 1000));
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }

//         }

//     }

//     @Override
//     public void end() {
//         for (Observer observer : observers) {
//             observer.end();
//         }
//         try {
//             myWriter.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     private void setMessage(String message) {
//         this.message = message;
//     }

//     public String getMessage() {
//         return message;
//     }

//     public void randomMessageState() {
//         this.messageState = MessageState.values()[new Random().nextInt(MessageState.values().length)];
//     }

//     public void randomMessageGroupNumber() {
//         Random r = new Random();
//         this.messageGroupNumber = r.nextInt((3 - 1) + 1) + 1;
//     }

//     public String getMessageState() {
//         return this.messageState.toString();
//     }

//     public int getMessageGroupNumber() {
//         return this.messageGroupNumber;
//     }

//     private int selectRandomObserver() {
//         Random r = new Random();
//         return this.messageGroupNumber = r.nextInt((10 - 1) + 1);
//     }

//     public int getRandomObserverNumber() {
//         return this.randomObserverNumber;
//     }

// }
