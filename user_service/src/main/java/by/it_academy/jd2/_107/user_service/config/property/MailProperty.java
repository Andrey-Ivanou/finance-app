package by.it_academy.jd2._107.user_service.config.property;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@AllArgsConstructor
@ConfigurationProperties(prefix = "app.mail")
public class MailProperty {

    private String host;
    private String port;
    private String login;
    private String password;
    private String from;
}
