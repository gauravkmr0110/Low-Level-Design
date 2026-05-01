// There are 4 ways to implement Singleton pattern

// Method-1 Lazy initialisation, its not thread safe multiple instances can be created by multiple threads

class LazySingleton{
    private static LazySingleton instance;

    private LazySingleton(){}

    public static LazySingleton getInstance(){
        if(instance==null){
            instance =  new LazySingleton();
        }
        return instance;
    }

    public void log(String message){
        System.out.println("Lazy Initiliazation called: " + message);
    }
}

// Method-2 Eager Initialization, thread safe but instance created even if its not used

class EagerSingleton{

    private final static EagerSingleton instance = new EagerSingleton();
    private EagerSingleton(){}

    public static EagerSingleton getInstance(){
        return instance;
    }

    public void log(String message){
        System.out.println("Eager Initialization called: " + message);
    }

}

// Method-3 Thread Safe singleton using synchronized but its slow
// since threads have to wait for lock to be realeased

class ThreadSafeSingleton{
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton(){}

    public static synchronized ThreadSafeSingleton getInstance(){
        if(instance==null){
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }

    public void log(String message){
        System.out.println("Thread safe initialization using synchronized called: " + message);
    }
}

// Method-4 recommended Double-Checked Locking thread safe and faster than method-3

class DCLSingleton{
    private static volatile DCLSingleton instance;

    private DCLSingleton(){}

    public static DCLSingleton getInstance(){
        if(instance == null){
            synchronized(DCLSingleton.class){
                if(instance == null){
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }

    public void log(String message){
        System.out.println("DCL initialization: " + message);
    }
}

public class Singleton {
    public static void main(String[] args) {

        LazySingleton lazy = LazySingleton.getInstance();
        lazy.log("Hello");

        EagerSingleton eager = EagerSingleton.getInstance();
        eager.log("Hello");

        ThreadSafeSingleton sync = ThreadSafeSingleton.getInstance();
        sync.log("Hello");

        DCLSingleton dcl = DCLSingleton.getInstance();
        dcl.log("Hello");

    }
}