// Name: Divya Bharathi I
// Reg no: 2117240020096

import java.sql.*;

public class JDBCConnection {
    public static void main(String[] args) {
    	System.out.println("Name : Divya Bharathi I");
    	System.out.println("Reg no : 2117240020096");
    	System.out.println();
    	
        String url = "jdbc:mysql://localhost:3306/student?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "Maha246@";

        // Optional: Explicitly load the driver (not required in modern JDBC)
        // Class.forName("com.mysql.cj.jdbc.Driver");

        // Test Case 1: Connect to DB successfully
        System.out.println("TC1: Connecting to database...");
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Output: Connected ");

            Statement stmt = conn.createStatement();

            // Test Case 2: Execute SELECT query on 'users' table
            System.out.println("\nTC2: Executing SELECT query on 'users'...");
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            boolean found = false;
            while (rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
            if (!found)
                System.out.println("Output: No records found ");
            else
                System.out.println("Output: Records displayed ");

            // Test Case 4: Execute empty query
            System.out.println("\nTC4: Executing empty query...");
            try {
                stmt.execute("");
                System.out.println("Output: No results ");
            } catch (SQLException e) {
                System.out.println("Output: " + e.getMessage());
            }

            // Test Case 5: Malformed SQL
            System.out.println("\nTC5: Executing malformed SQL...");
            try {
                stmt.executeQuery("SELEC * FRM users");
            } catch (SQLException e) {
                System.out.println("Output: SQLSyntaxErrorException  (" + e.getMessage() + ")");
            }

        } catch (SQLException e) {
            if (e.getMessage().contains("Access denied")) {
                System.out.println("TC3: Invalid credentials → Output: Access denied ✅");
            } else if (e.getMessage().contains("Unknown database")) {
                System.out.println("TC1: Database not found → Output: Unknown database ❌");
            } else {
                System.out.println("SQL Error: " + e.getMessage());
            }
        }
    }
}