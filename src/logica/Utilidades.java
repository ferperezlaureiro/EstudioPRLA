package logica;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.binary.Base64;

import consultaIUEwsdl_pkg.Giro;

public class Utilidades {
	
	public static String Encriptar(String texto) {
		 
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";
 
        try {
 
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
 
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
 
            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);
 
        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
 
    public static String Desencriptar(String textoEncriptado) throws Exception {
 
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";
 
        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
 
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
 
            byte[] plainText = decipher.doFinal(message);
 
            base64EncryptedString = new String(plainText, "UTF-8");
 
        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
    
	public static boolean estaDentroDeFechas(String fechaInicio, String fechaMovimiento, String fechaFin){
		if(fechaInicio==fechaMovimiento)
			return true;
		if(fechaFin==fechaMovimiento)
			return true;
		if(fechaFin==fechaInicio)
			return false;
		

		String[] auxFechaInicio = fechaInicio.split("/");
		String[] auxFechaFin = fechaFin.split("/");
		String[] auxFechaMovimiento = fechaMovimiento.split("/");
		
		Calendar fechaInicioCalendar = Calendar.getInstance();
		fechaInicioCalendar.set(Integer.parseInt(auxFechaInicio[2]),Integer.parseInt(auxFechaInicio[1])-1,Integer.parseInt(auxFechaInicio[0]));

		Calendar fechaMovimientoCalendar = Calendar.getInstance();
		fechaMovimientoCalendar.set(Integer.parseInt(auxFechaMovimiento[2]),Integer.parseInt(auxFechaMovimiento[1])-1,Integer.parseInt(auxFechaMovimiento[0]));
		
		if(fechaInicioCalendar.compareTo(fechaMovimientoCalendar)>0)
			return false;
		
		Calendar fechaFinCalendar = Calendar.getInstance();
		fechaFinCalendar.set(Integer.parseInt(auxFechaFin[2]),Integer.parseInt(auxFechaFin[1])-1,Integer.parseInt(auxFechaFin[0]));
		

		if(fechaFinCalendar.compareTo(fechaMovimientoCalendar)<0)
			return false;
		
		return true;
	}
	
	public static String armarMensage(Giro giro){
		return giro.getDecreto()+"&"+giro.getFecha()+"&"+giro.getTipo()+"&"+giro.getVencimiento();
	}

 	public static void SendMail(String To, String Subject, String Mensage) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
 
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("estudioprla@gmail.com", "EstudioPRLA-2016");
                    }
                });
 
        try {
 
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("estudioprla@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(To));
            message.setSubject(Subject);
            message.setText(Mensage);
 
            Transport.send(message);
 
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
