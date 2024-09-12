package org.example.services.implementations;
    import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailServiceImp {


        public static void main(String[] args) {
            String to = "imadbpro63@gmail.com";
            String from = "ibouderoua63@gmail.com";
            String host = "smtp.gmail.com";

            Properties properties = System.getProperties();

            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465"); // or 587
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("ibouderoua63@gmail.com", "ogdwpnrmuteilnyl"); // your password
                }
            });

            session.setDebug(true);

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject("Test Email from Java");
                message.setText("Hello, this is a test email sent from Java using Gmail!");

                Transport.send(message);
                System.out.println("Email sent successfully!");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }


}
