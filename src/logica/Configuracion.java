package logica;

import java.sql.Date;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;

public class Configuracion {
	public Configuracion () {
        BasicConfigurator.configure();
        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
        cargarUsuarioPrueba();
	}
	

	private void cargarUsuarioPrueba() {
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
		
        //Se ingresa un nuevo usuario
        Usuario u = new Usuario("Master", "Master1!", "Master", "49290325", "master@gmail.com", "099954750", "091410102", 
				"Satint bois 5063", "Treinta y tres 1334", "123123122123", new Date(11,12,1992));
		
		//Se guarda el nuevo usuario en la base de datos
		s.save(u);
		s.getTransaction().commit();
		
        s.disconnect();
	}
	
}
