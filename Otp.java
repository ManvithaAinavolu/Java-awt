import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

public class Otp {

    public static void main(String[] args) {
        final String username = "your_email@gmail.com"; // Your email
        final String password = "your_password"; // Your email password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("your_email@gmail.com")); // Same email
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("recipient_email@example.com")); // Recipient's email
            message.setSubject("Your One Time Password");

            // Generate the OTP
            String otp = generateOTP();

            message.setText("Your OTP is: " + otp);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to generate a 6-digit OTP
    private static String generateOTP() {
        // Your logic for generating a random OTP here
        // For example, using Random class
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}
