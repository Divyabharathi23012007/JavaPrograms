import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// ===== MODEL =====
class Employee {
    private int id;
    private String name;
    private String role;
    private double salary;

    public Employee(int id, String name, String role, double salary) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.salary = salary;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public double getSalary() { return salary; }

    public void setName(String name) { this.name = name; }
    public void setRole(String role) { this.role = role; }
    public void setSalary(double salary) { this.salary = salary; }
}

// ===== DAO (Data Access / "HR Database") =====
class EmployeeDAO {
    private final java.util.List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    public void updateEmployee(Employee emp) {
        for (Employee e : employees) {
            if (e.getId() == emp.getId()) {
                e.setName(emp.getName());
                e.setRole(emp.getRole());
                e.setSalary(emp.getSalary());
                break;
            }
        }
    }

    public void deleteEmployee(int id) {
        employees.removeIf(e -> e.getId() == id);
    }

    public java.util.List<Employee> getAllEmployees() {
        return employees;
    }

    public Employee getById(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) return e;
        }
        return null;
    }
}

// ===== VIEW =====
class EmployeeView extends JFrame {
    JTextField idField = new JTextField(10);
    JTextField nameField = new JTextField(10);
    JTextField roleField = new JTextField(10);
    JTextField salaryField = new JTextField(10);

    JButton addBtn = new JButton("Add");
    JButton updateBtn = new JButton("Update");
    JButton deleteBtn = new JButton("Delete");
    JButton refreshBtn = new JButton("Refresh");

    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Role", "Salary"}, 0);
    JTable table = new JTable(tableModel);

    public EmployeeView() {
        setTitle("Employee Management System (MVC)");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Employee Details"));
        inputPanel.add(new JLabel("ID"));
        inputPanel.add(new JLabel("Name"));
        inputPanel.add(new JLabel("Role"));
        inputPanel.add(new JLabel("Salary"));
        inputPanel.add(idField);
        inputPanel.add(nameField);
        inputPanel.add(roleField);
        inputPanel.add(salaryField);

        JPanel btnPanel = new JPanel();
        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(refreshBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}

// ===== CONTROLLER =====
class EmployeeController {
    private final EmployeeDAO model;
    private final EmployeeView view;

    public EmployeeController(EmployeeDAO model, EmployeeView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        view.addBtn.addActionListener(e -> addEmployee());
        view.updateBtn.addActionListener(e -> updateEmployee());
        view.deleteBtn.addActionListener(e -> deleteEmployee());
        view.refreshBtn.addActionListener(e -> loadEmployees());
        view.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = view.table.getSelectedRow();
                if (selectedRow != -1) {
                    view.idField.setText(view.table.getValueAt(selectedRow, 0).toString());
                    view.nameField.setText(view.table.getValueAt(selectedRow, 1).toString());
                    view.roleField.setText(view.table.getValueAt(selectedRow, 2).toString());
                    view.salaryField.setText(view.table.getValueAt(selectedRow, 3).toString());
                }
            }
        });
        loadEmployees();
    }

    private void addEmployee() {
        try {
            int id = Integer.parseInt(view.idField.getText());
            String name = view.nameField.getText();
            String role = view.roleField.getText();
            double salary = Double.parseDouble(view.salaryField.getText());
            model.addEmployee(new Employee(id, name, role, salary));
            view.showMessage("Employee added successfully!");
            loadEmployees();
        } catch (Exception ex) {
            view.showMessage("Invalid input!");
        }
    }

    private void updateEmployee() {
        try {
            int id = Integer.parseInt(view.idField.getText());
            String name = view.nameField.getText();
            String role = view.roleField.getText();
            double salary = Double.parseDouble(view.salaryField.getText());
            model.updateEmployee(new Employee(id, name, role, salary));
            view.showMessage("Employee updated successfully!");
            loadEmployees();
        } catch (Exception ex) {
            view.showMessage("Invalid input!");
        }
    }

    private void deleteEmployee() {
        try {
            int id = Integer.parseInt(view.idField.getText());
            model.deleteEmployee(id);
            view.showMessage("Employee deleted successfully!");
            loadEmployees();
        } catch (Exception ex) {
            view.showMessage("Invalid input!");
        }
    }

    private void loadEmployees() {
        view.tableModel.setRowCount(0);
        for (Employee e : model.getAllEmployees()) {
            view.tableModel.addRow(new Object[]{e.getId(), e.getName(), e.getRole(), e.getSalary()});
        }
    }
}

// ===== MAIN =====
public class EmployeeManagementMVC {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmployeeDAO model = new EmployeeDAO();
            EmployeeView view = new EmployeeView();
            new EmployeeController(model, view);
            view.setVisible(true);
        });
    }
}
