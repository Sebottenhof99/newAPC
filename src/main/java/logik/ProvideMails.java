package logik;



import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by fisch on 01.12.2017.
 */
public class ProvideMails  {

    static volatile int counter;



    public  void sendMail(Customer customer){
        final String usernameOfHostinger = "billing@hlworld.de";
        final String passwordOfHostinger = "9KTXaYyMxjzqolLJM";
        String mask = "Handy Lux";

        final String usernameOfGmail = "billing.handylux@gmail.com";
        final String passwordOfGmail = "Gmail291179";



        Properties propsOfHostinger = new Properties();
        propsOfHostinger.put("mail.smtp.auth", "true");
        propsOfHostinger.put("mail.smtp.starttls.enable", "true");
        propsOfHostinger.put("mail.smtp.host", "mx1.hostinger.de");
        propsOfHostinger.put("mail.smtp.port", "587");

        Properties propsOfGmail = new Properties();
        propsOfGmail.put("mail.smtp.auth", "true");
        propsOfGmail.put("mail.smtp.starttls.enable", "true");
        propsOfGmail.put("mail.smtp.host", "smtp.gmail.com");
        propsOfGmail.put("mail.smtp.port", "25");

        List<Properties> listOfProperties = new ArrayList<>();
        listOfProperties.add(propsOfHostinger);
        listOfProperties.add(propsOfGmail);

        Session session;
        String fromMail;


            session = Session.getInstance(listOfProperties.get(Defines.Mails.HOSTINGEN),
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(usernameOfHostinger, passwordOfHostinger);
                        }
                    });
            fromMail=usernameOfHostinger;



        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(fromMail,mask));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse( "fischbachadam92@gmail.com"));//customer.getMail()
            System.out.println(customer.getMail());
            message.setSubject("Ihre Rechnung zur Bestellung "+customer.getBestellnummer()+" von Handy Lux");
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(
                    "Sehr geehrte/r " + customer.getName()+","
                            + "\n\nVielen Dank für Ihre Bestellung mit der Nummer " + customer.getBestellnummer() + "!"
                            + "\n\n\nWaren Sie mit uns zufrieden?"
                            +"\nWenn ja, würden wir Sie bitten eine positive Bewertung zu hinterlassen um anderen Kunden dabei zu helfen eine bessere Entscheidung zu treffen."
                            +"\nWenn nein, kontaktieren Sie uns bitte über Ebay oder telefonisch unter der Nummer 0177 7840217.\n"
                            +"\nWir bemühen uns jedes Problem schnellstmöglich zu lösen!"
                            +"\n\nMit freundlichen Grüßen"
                            +"\n Ihr Handy Lux Team");

            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String file = "C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\Ama_Rechnungen\\work\\"+customer.getSavedFileName();
            String fileName = customer.getSavedFileName();
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            System.out.println("Sending");
            Transport.send(message);
            System.out.println("Done");
            counter++;

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}



