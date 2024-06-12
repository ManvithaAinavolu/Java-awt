import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class Login extends JPanel
 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Form");
        JPanel panel = new JPanel();

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        panel.add(new JLabel("Username: "));
        panel.add(usernameField);
        panel.add(new JLabel("Password: "));
        panel.add(passwordField);
        panel.add(loginButton);

        frame.add(panel);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        loginButton.addActionListener(e -> {
            String name = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            if (authenticateUser(name, pass)) {
                JOptionPane.showMessageDialog(null, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(null, "Login failed. Please check your credentials.");
            }
        });
    }

   private static boolean authenticateUser(String name, String pass) {
    try {
        Class.forName("oracle.jdbc.OracleDriver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        return false; 
    }

    String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
    String dbUsername = "system";
    String dbPassword = "manager";

    try {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        String query = "SELECT * FROM users WHERE name = ? AND pass = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, pass);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next(); 
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

}
