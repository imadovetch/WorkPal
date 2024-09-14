package org.example.services.implementations;
    import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;
public class EmailServiceImp {


        public static void main(String to ,String content) {

            String from = "ibouderoua63@gmail.com";
            String host = "smtp.gmail.com";

            Properties properties = System.getProperties();

            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465"); // or 587
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("ibouderoua63@gmail.com", "ogdwpnrmuteilnyl");
                }
            });

            session.setDebug(true);

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject("Email from Java");
                message.setText(content + "\n\nSent at: " + getCurrentTimestamp());

                Transport.send(message);
                System.out.println("Email sent successfully!");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }
    private static String getCurrentTimestamp() {
        LocalDateTime now = LocalDateTime.now(); // Get current date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Define format
        return now.format(formatter); // Return formatted timestamp
    }

}
