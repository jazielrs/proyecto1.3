//package domain;
//
///**
// *
// * @author Cristopher.za
// */
//import com.sun.mail.handlers.image_jpeg;
//import java.awt.Image;
//import java.awt.Panel;
//import java.util.Properties;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import javax.swing.ImageIcon;//Prohibidas***
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//
///**
// *
// * @author Dguerrero
// */
public class Mail {
//
//    Properties propiedad = new Properties();
//
//    public void run(String receptor, String asunto, String mensaje) {
//        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
//        propiedad.setProperty("mail.smtp.starttls.enable", "true");
//        propiedad.setProperty("mail.smtp.port", "587");
//        //propiedad.setProperty(¡);
//
//        Session sesion = Session.getDefaultInstance(propiedad);
//        String correoEnvia = "oneprojectalgo@gmail.com";
//        String contrasena = "oPA2020.";
//        MimeMessage mail = new MimeMessage(sesion);
//        MimeBodyPart body1 = new MimeBodyPart();
//        MimeBodyPart body2 = new MimeBodyPart();
//        MimeMultipart body = new MimeMultipart();
//        try {
//            mail.setFrom(new InternetAddress(correoEnvia));
//            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
//            mail.setSubject(asunto);
//            body1.setText(mensaje);
//            body2.setDataHandler(new DataHandler(new FileDataSource("ucr.jpg")));
//            body.addBodyPart(body1);
//            body.addBodyPart(body2);
//            mail.setContent(body);
//
//            Transport transportar = sesion.getTransport("smtp");
//            transportar.connect(correoEnvia, contrasena);
//            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
//
//        } catch (AddressException ex) {
//            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (MessagingException ex) {
//            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
}
