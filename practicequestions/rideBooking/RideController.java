package ridebooking;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class RideController {
    private final List<Ride> rides = new ArrayList<>();
    private final List<Driver> drivers = new ArrayList<>();
    private final Map<User,List<Ride>>userVsRides = new HashMap<>();
    // just for simplicity , we can have one more list of users for user data


    private RideController() {
    }

    public void registerDriver(Driver driver){
        drivers.add(driver);
    }

    public void removeDriver(Driver driver){
        drivers.remove(driver);
    }

    public void registerUser(User user){
        userVsRides.put(user,new ArrayList<>());
    }

    public void deleteUser(User user){
        userVsRides.remove(user);
    }

    public void createRide(Location src, Location dest){

        // create ride object

        // first estimated fare should be show to both user and driver 

        // assign to drivers using strategy then driver will take action whether to accept or cancel

        // if accept call start ride function

       

    }

    public void startRide(Ride ride){
        // ride status to in progress 
        // when ride reaches to dest, do the payemnt 
        // payment done 
        // mark ride completed 
        
    }



    




}
