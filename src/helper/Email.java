package helper;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {

    private static final int PORT = 587;

    private static final String HOST = "smtp.mail.yahoo.com";

    public static void sendEmail(String from, String pass, String to, String subject, String body) {

        Properties properties = System.getProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.port", PORT);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);

        MimeMessage message = new MimeMessage(session);
        try {
            Transport transport = session.getTransport();
            transport.connect(HOST, PORT, from, pass);
            message.setFrom(from);
            message.addRecipients(Message.RecipientType.TO, to);
            message.setSubject(subject);
            message.setText(body);
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("Sent Successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
