package logica;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

public class Listener implements javax.servlet.ServletContextListener {

	public void contextInitialized(ServletContext context) {
		new Configuracion();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		new Configuracion();
	}
}