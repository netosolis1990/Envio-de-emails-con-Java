package netosolis.com;
 
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class Mail {
    
    //Configura estos parametros de acuerdo a tu STMP, Si utilizas GMAIL solo cambia el usuario y password por tu email y pass
    Properties props = new Properties();
    final String host = "smtp.gmail.com";
    final String puerto = "465";
    final String usuario = "tuemai@gmail.com";
    final String password = "tupassword";
    
    public Mail(){
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.socketFactory.port", puerto);
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", puerto);
    }
    
    public boolean enviarEmail(String para,String asunto,String mensaje){
        try {
            Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(usuario,password);
				}
			});
            Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(usuario));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(para));
	    message.setSubject(asunto);
	    message.setText(mensaje);
            Transport.send(message);
            return true;
	} catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}