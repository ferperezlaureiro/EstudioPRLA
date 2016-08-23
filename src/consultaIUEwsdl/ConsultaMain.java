package consultaIUEwsdl;
import java.rmi.RemoteException;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import consultaIUEwsdl_pkg.*;
public class ConsultaMain {

	public static void main(String[] args) {
		ConsultaIUEwsdlPortTypeProxy consulta = new ConsultaIUEwsdlPortTypeProxy();
		try {
			Resultado resultado = consulta.consultaIUE("2-36949/2015");
			Giro[] movimientos = resultado.getMovimientos();
			System.out.println(movimientos[0].getFecha());
			SendMail("fperezlaureiro@gmail.com", "HOLA");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("finally")
	public static String[] TieneMovimiento(String IUE) {
		ConsultaIUEwsdlPortTypeProxy consulta = new ConsultaIUEwsdlPortTypeProxy();
		String[] movimientoHoy = null;
		try {
			Resultado resultado = consulta.consultaIUE("2-36949/2015");
			Giro[] movimientos = resultado.getMovimientos();
			System.out.println(movimientos[0].getSede());
			String fecha = "";
			Date today = new Date();
			int movimientosCounter = 0;
			for (int i = 0; i < movimientos.length; i++) {
				fecha = movimientos[i].getFecha();
				String[] auxFecha = fecha.split("/");
				if (auxFecha[0].equals(today.getDay()) && 
					auxFecha[1].equals(today.getMonth()) && 
					auxFecha[2].equals(today.getYear()) ) {
					movimientoHoy[movimientosCounter] = armarMensage(movimientos[i]);
					movimientosCounter++;
				} else {
					break;
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			return movimientoHoy;
		}
	}
	
	private static String armarMensage(Giro giro){
		return giro.getDecreto()+"&"+giro.getFecha()+"&"+giro.getTipo()+"&"+giro.getVencimiento();
	}

 	public static void SendMail(String To, String Mensage) {
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
            message.setSubject("Nuevo Movimiento");
            message.setText(Mensage);
 
            Transport.send(message);
 
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
