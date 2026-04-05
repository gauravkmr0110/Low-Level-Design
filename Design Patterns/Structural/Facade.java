/*
Useful when we have to hide system complexity from client

Instead of interacting with multiple complicated subsystems directly, you interact with a
 single unified interface (the facade) that handles everything behind the scenes.

Use the Facade pattern when:

A system is complex or has many interdependent classes
You want to reduce dependencies between client code and subsystems(lose coupling)
You need a clean, easy-to-use API for a larger body of code
You're integrating legacy systems or third-party libraries


    Client --> Facade

    Facade --> SubsystemA // has relationship
    Facade --> SubsystemB
    Facade --> SubsystemC

    Client depends only on Facade,
    which reduces coupling and hides complexity of subsystems.
    Facade acts as a single entry point, so client is decoupled from internal
    subsystem complexity
 */


// Subsystem classes

class SeatBookingService{
    public boolean bookSeat(String seatNo){
        System.out.println("Seat " + seatNo + " Booked");
        return true;
    }
}

class PaymentService{
    public boolean makePayemt(int amount){
        System.out.println("Payment of " + amount + " Done");
        return true;
    }
}

class NotificationService{
    public void sendNotification(String message){
        System.out.println("Notification sent: " + message);
    }
}

// Facade Layer
class MovieBookingFacade{
    private SeatBookingService seatBookingService;
    private PaymentService paymentService;
    private NotificationService notificationService;

    public MovieBookingFacade(){
        seatBookingService = new SeatBookingService();
        paymentService = new PaymentService();
        notificationService = new NotificationService();
    }

    public void bookTicket(String seatNo, int amount){
        if(seatBookingService.bookSeat(seatNo) &&
                paymentService.makePayemt(amount)) {
            notificationService.sendNotification("Ticket booked successfully");

        }
    }
}

public class Facade {
    public static void main(String args[]){
        MovieBookingFacade facace = new MovieBookingFacade();
        facace.bookTicket("A1",200);
    }

    /*
    without facade:------

    SeatBooking seat = new SeatBooking();
    Payment payment = new Payment();
    Notification notification = new Notification();

    if(seat.bookSeat("A1")) {
        if(payment.pay(200)) {
            notification.send("Ticket booked!");
        }
    }
     */
}