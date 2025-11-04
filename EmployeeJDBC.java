import java.sql.*;

public class EmployeeJDBC {
    public static void main(String[] args) {
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb", "root", "Maha246@");

            Statement stmt = con.createStatement();

            // Drop table if it exists (for clean demo)
            stmt.executeUpdate("DROP TABLE IF EXISTS employee");

            // Create new table
            String createTable = "CREATE TABLE employee (" +
                                 "emp_no INT PRIMARY KEY, " +
                                 "emp_name VARCHAR(50), " +
                                 "city VARCHAR(50))";
            stmt.executeUpdate(createTable);

            // Insert rows
            stmt.executeUpdate("INSERT INTO employee VALUES (1, 'ABC', 'Chennai')");
            stmt.executeUpdate("INSERT INTO employee VALUES (2, 'XYZ', 'Delhi')");

            // Display result with column headers
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
            ResultSetMetaData meta = rs.getMetaData();

            // Print column headers
            int columnCount = meta.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(meta.getColumnName(i) + "\t");
            }
            System.out.println();

            // Print rows
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

            // Close connection
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}