import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host" , "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.port" , "465");
        props.put("mail.smtp.auth" , "true");
        props.put("mail.smtp.ssl.enable" , "false");
        props.put("mail.smtp.starttls.enable" , "true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(
                        "3b7130828b9619",
                        "f7788ecbb59adb"
                );
            };
        };
        Session session = Session.getInstance(props , authenticator);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("akrombeksodiqov66@gmail.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("akrombeksodiqov5@gmail.com"));
        message.setSubject("Testing of sending mail");
        message.setContent("""
                <html>
                <body>
                <h2 style = "color : green; text-align: center;"> Pdp Academy </h2>
                <p style = "color : blue; text-align : center;"> Hello Dear Student
                It nice to have in our academy
                </p>
                </body>
                </html>
                """ , "text/html; charset=utf-8");
        message.setText("Dear Developer, \n You just crashed our server with code. \n Please revert back all you trash code.");
        Transport.send(message);
    }
}