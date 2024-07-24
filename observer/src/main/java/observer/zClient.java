package observer;

// public class Client {
//     public static void main(String[] args) {
//         // 1 Creating a subject first
//         ConcreteSubject concreteSubject = new ConcreteSubject( "state1" );

// 		// Observer observer1 = new ConcreteObserver( concreteSubject, "observer1" );
// 		// Observer observer2 = new ConcreteObserver( concreteSubject, "observer2" );
// 		// concreteSubject.setState( "state2" );
// 		// concreteSubject.setState( "state3" );


//         //2 Then create the observers (broadcasters)
//         for (int i = 1; i <= 10; i++) {
//             ConcreteObserver observer = new ConcreteObserver();
//             s.addObserver(observer);
//             new Thread(observer).start();
//         }
//     }
// }