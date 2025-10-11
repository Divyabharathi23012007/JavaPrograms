import java.sql.*;

public class JDBCConnectionDemo {
    public static void main(String[] args) {
        try {
            // Step 1: Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Define the connection URL
            String url = "jdbc:mysql://localhost:3306/testdb";
            String user = "root";
            String password = "Maha246@"; // Replace with your actual password

            // Step 3: Establish the connection
            Connection conn = DriverManager.getConnection(url, user, password);

            // Step 4: Create the Statement object
            Statement stmt = conn.createStatement();

            // Step 5: Execute a query
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            // Step 6: Process the results
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }

            // Step 7: Close the connection
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}