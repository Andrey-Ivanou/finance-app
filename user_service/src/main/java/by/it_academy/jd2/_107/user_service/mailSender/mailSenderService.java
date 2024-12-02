package by.it_academy.jd2._107.user_service.mailSender;

import by.it_academy.jd2._107.user_service.config.property.MailProperty;
import by.it_academy.jd2._107.user_service.mailSender.api.INotificationSenderService;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class mailSenderService implements INotificationSenderService {

    private final MailProperty mailProperty;
    private final Properties prop;

    public mailSenderService(MailProperty mailProperty) {
        this.mailProperty = mailProperty;

        prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.host", mailProperty.getHost());
        prop.put("mail.smtp.port", mailProperty.getPort());
    }

    @Async
    @Override
    public void send(String mail, String code) {
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        mailProperty.getLogin(),
                        mailProperty.getPassword()
                );
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailProperty.getFrom()));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(mail));

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(code, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (AddressException e){
            throw new RuntimeException("Ошибка валидации получателя", e);
        }
        catch (MessagingException e){
            throw new RuntimeException("Ошибка отправки письма", e);
        }
    }
}
