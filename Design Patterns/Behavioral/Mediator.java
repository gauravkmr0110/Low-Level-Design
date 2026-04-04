/*Mediator design pattern is used when multiple objects need to communicate frequently.
Instead of objects communicating directly (which creates tight coupling),
they communicate through a central mediator that handles all interactions.

Example: Auction, Air Traffic Contol system

        +------------------+
                |    Mediator      |
        +------------------+
        | +sendMessage()   |
        +--------+---------+
        |
        +-----------+-----------+
        |                       |
        +-------------+     +------------------+
        | Colleague   |     | ConcreteMediator |
        +-------------+     +------------------+
        | mediator ref|     | colleagues list  |
        | +send()     |     | +sendMessage()   |
        | +receive()  |     +------------------+
        ^
        |
        +-------------------+
        | ConcreteColleague |
        +-------------------+
        | +send()           |
        | +receive()        |
        +-------------------+
*/

import java.util.*;

// Mediator Interface
interface AuctionMediator{
    void addBidder(Bidder bidder);
    public void placeBid(Bidder bidder, int bidAmount);
}

// Concreete Mediator
class Auction implements AuctionMediator{
    List<Bidder>bidders = new ArrayList<>();
    private int highestBidAmount;
    private Bidder highestBidder;

    @java.lang.Override
    public void addBidder(Bidder bidder) {
        bidders.add(bidder);
    }

    @java.lang.Override
    public void placeBid(Bidder bidder, int bidAmount) {
        if(bidAmount>highestBidAmount){
            highestBidAmount = bidAmount;
            highestBidder = bidder;
            System.out.println(bidder.getName() + " placed bid of " + bidAmount);

            // now we have to notify all other bidders
            for(Bidder temp : bidders){
                if(temp!=bidder){
                    temp.receiveBidNotification(bidAmount);
                }
            }
        }
        else{
            System.out.println("Error, please place a bid greater than current highest bid of "+ highestBidAmount);
        }
    }

    public Bidder getHighestBidder() {
        return highestBidder;
    }

    public int getHighestBidAmount() {
        return highestBidAmount;
    }
}

// Colleague Interface
interface Colleague{
    void placeBid(int bidAmount);
    void receiveBidNotification(int bidAmount); // if some other bidder placd the bid
    String getName();
}

// Concrete Colleague
class Bidder implements Colleague{
    String name;
    AuctionMediator auctionMediator;

    Bidder(String name, AuctionMediator auctionMediator){
        this.name = name;
        this.auctionMediator = auctionMediator;
        auctionMediator.addBidder(this);

    }

    @java.lang.Override
    public void placeBid(int bidAmount) {
        auctionMediator.placeBid(this,bidAmount);
    }

    @java.lang.Override
    public void receiveBidNotification(int bidAmount) {
        System.out.println(name + " notified, highest bid now is: " + bidAmount);
    }

    @java.lang.Override
    public String getName() {
        return name;
    }
}

// Driver class for mediator pattern
public class Mediator {
    public static void main(String[] args){

        AuctionMediator auction = new Auction();

        Bidder bidder1 = new Bidder("A", auction);
        Bidder bidder2 = new Bidder("B", auction);
        Bidder bidder3 = new Bidder("C", auction);

        bidder1.placeBid(100);
        bidder2.placeBid(150);
        bidder3.placeBid(120);

        System.out.println("Highest Bidder: " + ((Auction)auction).getHighestBidder().getName());
        System.out.println("Highest Bid Amount: " + ((Auction)auction).getHighestBidAmount());
    }
}
