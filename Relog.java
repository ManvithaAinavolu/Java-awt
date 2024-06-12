import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
public class Relog {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Registration and Login App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Registration registrationForm = new Registration();
        Login loginForm = new Login();
        frame.add(registrationForm);
        JButton loginButton = new JButton("Switch to Login");
        frame.add(loginButton);

        // Add an action listener to the button to switch forms
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(registrationForm); // Remove the registration form
                frame.add(loginForm); // Add the login form
                frame.revalidate(); // Refresh the frame to reflect changes
            }
        });

        // Set frame properties
        frame.setSize(300, 150);
        frame.setVisible(true);
    }
}
