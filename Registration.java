import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.HashMap;
import javax.swing.*;
public class Registration extends JPanel {
    private TextField nameField;
    private TextField emailField;
      private HashMap<String, String> userDatabase;

    public Registration() {
        setTitle("Registration form");
        setBounds(300, 90, 900, 600);
        Color formColor = new Color(53, 59, 72);
		setBackground(formColor);
         setResizable(false);
        setLayout(new GridLayout(4, 2, 10, 10));
        setLayout(new FlowLayout());
        Label head=new Label("WELCOME TO REGISTRATION !!");
        head.setFont(new Font("Arial",Font.BOLD,20));
        head.setForeground(Color.YELLOW);
            add(head);
            add(new Label());
            add(new Label());
        add(new Label());
        Label nameLabel = new Label("Name:");
        nameField = new TextField(30);
        nameLabel.setForeground(Color.WHITE);
         add(nameLabel);
        add(nameField);
        add(new Label());
        add(new Label());
        Label emailLabel = new Label("Email:");
        emailField = new TextField(30);
        emailLabel.setForeground(Color.WHITE);
        add(emailLabel);
        add(emailField);
        add(new Label());
          add(new Label());
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField(30);
        passwordField.setEchoChar('*');
        passwordLabel.setForeground(Color.WHITE);
        add(passwordLabel);
        add(passwordField);
        add(new Label());
          add(new Label());
        Button regButton = new Button("Register");
          regButton.setBackground(Color.BLUE); 
        regButton.setForeground(Color.WHITE); 
    
        
       
        add(new Label());
          add(new Label());
        
        
        add(regButton);

       regButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String email = emailField.getText();
        String pass=passwordField.getText();
        if (name.isEmpty() || email.isEmpty()) {
            System.out.println("Please provide both name and email.");
        } else {
            try {
                Class.forName("oracle.jdbc.OracleDriver"); 
                String url = "jdbc:oracle:thin:@localhost:1521:xe";
                String username = "system";
                String password = "manager";

                Connection connection = DriverManager.getConnection(url, username, password);
                String insertQuery = "INSERT INTO users (name, email,pass) VALUES (?, ?,?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3,pass);
                    int rowsInserted = preparedStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(Registration.this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Registration successful!");
                    } else {
                        JOptionPane.showMessageDialog(Registration.this, "Registration failed!", "Failed", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Registration failed.");
                    }
                nameField.setText("");
                emailField.setText("");
                passwordField.setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            
                String selectQuery = "SELECT * FROM users";
                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                     ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String retrievedName = resultSet.getString("name");
                        String retrievedEmail = resultSet.getString("email");
                        System.out.println("Name: " + retrievedName + " Email: " + retrievedEmail);
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
});

    }

    public static void main(String[] args) {
        Registration form = new Registration();
        form.setVisible(true);

        form.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
