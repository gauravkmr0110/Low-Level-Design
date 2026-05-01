/*
when we have multiple ways to do same thing, we use startegy pattern

          +----------------------+
          |      Context         |
          +----------------------+
          | - strategy: Strategy |
          +----------------------+
          | +setStrategy()       |
          | +executeStrategy()   |
          +----------+-----------+
                     |
                     v
          +----------------------+
          |      Strategy        |   <<interface>>
          +----------------------+
          | +execute()           |
          +----------+-----------+
                     ^
         ------------|-------------
         |                          |
+---------------------+   +---------------------+
|   ConcreteStrategyA |   |   ConcreteStrategyB |
+---------------------+   +---------------------+
| +execute()          |   | +execute()          |
+---------------------+   +---------------------+
 */

// Startegy Interface
interface PaymentStrategy{
    void makePayment(int amount);
}

// Concrete Strategies
class UPIPaymentStrategy implements PaymentStrategy{
    @java.lang.Override
    public void makePayment(int amount) {
        System.out.println("Payment done by upi of amount "+amount);
    }
}

class CardPaymentStrategy implements PaymentStrategy{
    @java.lang.Override
    public void makePayment(int amount) {
        System.out.println("Payment done by card of amount "+amount);
    }
}

//Context uses Strategy
class PaymentService{
    private PaymentStrategy paymentStrategy;

    PaymentService(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy strategy){
        paymentStrategy = strategy;
    }

    public void doPayment(int amount){
        paymentStrategy.makePayment(amount);
    }
}




public class Strategy {
    public static void main(String args[]){
        PaymentService paymentService = new PaymentService(new CardPaymentStrategy());
        paymentService.doPayment(1000);
        paymentService.setPaymentStrategy(new UPIPaymentStrategy());
        paymentService.doPayment(2000);
    }
}