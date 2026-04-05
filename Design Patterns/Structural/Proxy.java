/*
    Instead of directly interacting with the real object, the client interacts with a proxy,
    which can add extra behavior like lazy loading, access control, logging, caching, etc.

    Client → Proxy → Real Object
    *** Both Proxy and Real Object implement the same interface.

    1. Access control: we can use proxy patternt to restrict access to sensitive
    information depending on user permissions

    2. pre and post processing

    Proxy → RealSubject → has-a relationship
    Proxy & RealSubject → Subject → implements
    Client → Subject → depends on abstraction

    Client → Subject (interface)
            ▲
            |
          Proxy → RealSubject

 */

enum EmployeeRole {
    USER,
    ADMIN
}

// Subject
interface EmployeeDAO {
    void getEmployeeDetails(int empId);
    void createEmployee(String name);
}

// Real Object
class RealEmployeeDAO implements EmployeeDAO {

    public RealEmployeeDAO() {
        System.out.println("RealEmployeeDAO initialized");
    }

    @Override
    public void getEmployeeDetails(int empId) {
        System.out.println("Getting employee details of empId " + empId);
    }

    @Override
    public void createEmployee(String name) {
        System.out.println("Employee created: " + name);
    }
}

// Proxy Object
class ProxyEmployeeDAO implements EmployeeDAO {

    private RealEmployeeDAO realEmployeeDAO; // lazy init
    private EmployeeRole clientRole;

    public ProxyEmployeeDAO(EmployeeRole role) {
        this.clientRole = role;
    }

    // Lazy initialization
    private RealEmployeeDAO getRealDAO() {
        if (realEmployeeDAO == null) {
            realEmployeeDAO = new RealEmployeeDAO();
        }
        return realEmployeeDAO;
    }

    @Override
    public void getEmployeeDetails(int empId) {
        // No restriction, but still lazy
        getRealDAO().getEmployeeDetails(empId);
    }

    @Override
    public void createEmployee(String name) {
        if (clientRole == EmployeeRole.ADMIN) {
            getRealDAO().createEmployee(name);
        } else {
            System.out.println("Access Denied: Only ADMIN can create employee");
        }
    }
}

// Factory (hides Proxy creation)
class EmployeeFactory {
    public static EmployeeDAO getEmployeeDAO(EmployeeRole role) {
        return new ProxyEmployeeDAO(role);
    }
}

// Client
public class Proxy{
    public static void main(String[] args) {

        EmployeeDAO userClient = EmployeeFactory.getEmployeeDAO(EmployeeRole.USER);

        userClient.getEmployeeDetails(1);   // allowed
        userClient.createEmployee("Rohit"); // denied

        System.out.println("----");

        EmployeeDAO adminClient = EmployeeFactory.getEmployeeDAO(EmployeeRole.ADMIN);

        adminClient.getEmployeeDetails(2);  // allowed
        adminClient.createEmployee("Virat"); // allowed
    }
}