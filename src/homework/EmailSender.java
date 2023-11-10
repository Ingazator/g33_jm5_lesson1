package homework;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    private final String username;
    private final String password;
    private final Properties properties;

    public EmailSender(String username, String password) {
        this.username = username;
        this.password = password;

        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        properties.put("mail.smtp.port", "587");
    }

    public void sendEmail(String recipient, String subject, String content) {
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Pochta yuborildi!");
        } catch (MessagingException e) {
            System.out.println("Xatolik yuz berdi: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Pochta hisobingizning foydalanuvchisi: ");
        String username = scanner.nextLine();

        System.out.print("Parol: ");
        String password = scanner.nextLine();

        System.out.print("Qabul qiluvchi pochta manzili: ");
        String recipient = scanner.nextLine();

        System.out.print("Mavzu: ");
        String subject = scanner.nextLine();

        System.out.print("Xabar matni: ");
        String content = scanner.nextLine();

        EmailSender emailSender = new EmailSender(username, password);
        emailSender.sendEmail(recipient, subject, content);
    }
}