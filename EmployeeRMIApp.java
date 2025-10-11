 import java.io.Serializable;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;

// ===========================
// Serializable Employee Class
// ===========================
class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    public void setDepartment(String department) { this.department = department; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', dept='" + department + "', salary=" + salary + "}";
    }
}

// ==================================
// Remote Interface for Employee RMI
// ==================================
interface EmployeeService extends Remote {
    boolean addEmployee(Employee e) throws RemoteException;
    Employee getEmployee(int id) throws RemoteException;
    List<Employee> listEmployees() throws RemoteException;
    boolean updateEmployee(Employee e) throws RemoteException;
    boolean deleteEmployee(int id) throws RemoteException;
}

// ==========================================
// Implementation of Remote Employee Service
// ==========================================
class EmployeeServiceImpl extends UnicastRemoteObject implements EmployeeService {
    private static final long serialVersionUID = 1L;
    private Map<Integer, Employee> store = new HashMap<>();

    protected EmployeeServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized boolean addEmployee(Employee e) throws RemoteException {
        if (store.containsKey(e.getId())) return false;
        store.put(e.getId(), e);
        return true;
    }

    @Override
    public synchronized Employee getEmployee(int id) throws RemoteException {
        return store.get(id);
    }

    @Override
    public synchronized List<Employee> listEmployees() throws RemoteException {
        return new ArrayList<>(store.values());
    }

    @Override
    public synchronized boolean updateEmployee(Employee e) throws RemoteException {
        if (!store.containsKey(e.getId())) return false;
        store.put(e.getId(), e);
        return true;
    }

    @Override
    public synchronized boolean deleteEmployee(int id) throws RemoteException {
        return store.remove(id) != null;
    }
}

// =============================
// Server Class (RMI Registry)
// =============================
class EmployeeServer {
    public static void main(String[] args) {
        try {
            EmployeeService service = new EmployeeServiceImpl();

            // Adding sample employees
            service.addEmployee(new Employee(1, "Alice", "HR", 55000));
            service.addEmployee(new Employee(2, "Bob", "IT", 70000));

            // Create and bind registry automatically (no need to start rmiregistry)
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost/EmployeeService", service);

            System.out.println("✅ Employee RMI Server is running...");
            System.out.println("Service bound as: rmi://localhost/EmployeeService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// =============================
// Client Class (RMI Consumer)
// =============================
public class EmployeeRMIApp {
    public static void main(String[] args) {
        try {
        	System.out.println(" Name       : Divya Bharathi I");
            System.out.println(" Reg Number : 2117240020096");
            EmployeeService service = (EmployeeService) Naming.lookup("rmi://localhost/EmployeeService");
            System.out.println("Connected to Employee Service");

            System.out.println("\nInitial Employees:");
            for (Employee e : service.listEmployees()) {
                System.out.println(e);
            }

            // Add new employee0
            Employee newEmp = new Employee(3, "Charlie", "Finance", 60000);
            service.addEmployee(newEmp);
            System.out.println("\nAdded: " + newEmp);

            // Update employee
            Employee emp = service.getEmployee(2);
            emp.setSalary(75000);
            service.updateEmployee(emp);
            System.out.println("\nUpdated Employee 2 salary to 75000");

            // Delete employee
            service.deleteEmployee(1);
            System.out.println("\nDeleted Employee 1");

            // Final list
            System.out.println("\nFinal Employees:");
            for (Employee e : service.listEmployees()) {
                System.out.println(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n⚠️ Run the EmployeeServer class first before running this client.");
        }
    }
}
