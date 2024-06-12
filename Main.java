import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        String name = "John Doe";
        String email = "john@example.com"; 
        String pass = "secretpassword"; // Change this to your actual password
        
        try {
            Class.forName("oracle.jdbc.OracleDriver"); // Load the driver
            String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Change as needed
            String username = "system";
            String password = "manager";
            
            Connection connection = DriverManager.getConnection(url, username, password);

            String insertQuery = "INSERT INTO users (name, email, pass) VALUES (?, ?, ?)";
        

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, pass); // Change variable name here
                int rowsInserted = preparedStatement.executeUpdate();
                
                if (rowsInserted > 0) {
                    System.out.println("Registration successful!");
                 
                } else {
                    System.out.println("Registration failed.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
             String selectQuery = "SELECT * FROM users";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectQuery)) {
                while (resultSet.next()) {
                    String retrievedName = resultSet.getString("name");
                    String retrievedEmail = resultSet.getString("email");
                    String retrievedPass = resultSet.getString("pass");
                    System.out.println(retrievedName + " " + retrievedEmail + " " + retrievedPass);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            connection.close(); 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}