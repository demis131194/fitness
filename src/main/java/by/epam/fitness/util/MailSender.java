package by.epam.fitness.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * The type Mail sender.
 */
public class MailSender {
    private static Logger logger = LogManager.getLogger(MailSender.class);

    private static final String PROPERTY_PATH = "mail/mail.properties";
    private static final String SUBJECT_MAIL = "Verification";
    private static final String TEXT_MAIL = "Head to this link for activation your account: <a href='http://localhost:8080/fitness/controller?command=verification&userId=%d'>verification</a>";
    private static Properties properties;

    static {
        properties = PropertyLoader.loadProperty(PROPERTY_PATH);
    }

    /**
     * Send verification message.
     *
     * @param recipientMail the recipient mail
     * @param clientId      the client id
     */
    public static void sendVerificationMessage(String recipientMail, int clientId) {
        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(properties.getProperty("mail.smtp.user")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientMail));

            message.setSubject(SUBJECT_MAIL);
            message.setText(String.format(TEXT_MAIL, clientId), StandardCharsets.UTF_8.displayName(), "html");

            try (Transport transport = session.getTransport("smtp")) {
                transport.connect("smtp.gmail.com", properties.getProperty("mail.smtp.user"), properties.getProperty("mail.smtp.password"));
                transport.sendMessage(message, message.getAllRecipients());
            }

        } catch (MessagingException e) {
            logger.error(e);
        }
    }
}
