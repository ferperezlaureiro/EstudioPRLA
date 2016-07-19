package logica;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class ControladoraCaso {
	//PRINCIPIO SECCION CONSULTAS
	public static Caso obtenerCasoPorIUE (String usuarioActual, String iUE) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
				
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
		
        Query query = s.createQuery("from Caso where IUE = :iUE ");
        query.setParameter("iUE", iUE);
        List list = query.list();
        
        Caso c = (Caso)list.get(0);
        
        s.getTransaction().commit();

        if(c != null) {
        	return c;
        } else {
        	return null;
        }
	}
	
	public static ArrayList<Mensaje> obtenerConversacion (String usuarioActual, String iUE) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
		
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
		
        Query query = s.createQuery("from Mensaje where idCaso = :iUE ");
        query.setParameter("iUE", iUE);
        List list = query.list();
        
        ArrayList<Mensaje> mensajes = new ArrayList<>();
        if(!list.isEmpty()) {
        	for(Object o: list) {
        		mensajes.add((Mensaje)o);
        	}
        }
        
        s.getTransaction().commit();

        return mensajes;
	}
	
	public static ArrayList<Involucrado> obtenerInvolucrados (String usuarioActual, String iUE) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
		
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
		
        Query query = s.createQuery("from Involucrado where idCaso = :iUE ");
        query.setParameter("iUE", iUE);
        List list = query.list();
        
        ArrayList<Involucrado> mensajes = new ArrayList<>();
        if(!list.isEmpty()) {
        	for(Object o: list) {
        		mensajes.add((Involucrado)o);
        	}
        }
        
        s.getTransaction().commit();

        return mensajes;
	}
	//FIN SECCION CONSULTAS
	
	//PRINCIPIO SECCION INSERCIONES
	public static long agregarCaso(String usuarioActual, String iUE, String juzgado, int turno, String caratulado) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();

        Caso c = new Caso(iUE, juzgado, turno, caratulado);
		
		//Se guarda el nuevo caso en la base de datos
		long idCaso = ((Caso)s.save(c)).getId();
		s.getTransaction().commit();
		
		return idCaso;
	}
	
	public static void agregarInvolucrado(String usuarioActual, String iUE, Date fechaDeNacimiento, String nombre, String cedula, 
			String nacionalidad, String direccion,String clase) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();

        Involucrado i = new Involucrado(obtenerCasoPorIUE(usuarioActual, iUE).getId(), fechaDeNacimiento, nombre, cedula, nacionalidad, direccion, clase);
		
		//Se guarda el nuevo involucrado en la base de datos
		s.save(i);
		s.getTransaction().commit();		
	}
	
	public static void agregarMensaje(String usuarioActual, String iUE,  String usuario, Date fecha, String contenido) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();

        Mensaje m = new Mensaje(obtenerCasoPorIUE(usuarioActual, iUE).getId(), ControladoraUsuario.buscarUsuario(usuario).getId(), fecha, contenido);
		
		//Se guarda el nuevo involucrado en la base de datos
		s.save(m);
		s.getTransaction().commit();		
	}
	//FIN SECCION INSERCIONES
	
	//PRINCIPIO SECCION BAJAS
	public static String eliminarCaso(String usuarioActual, String iUE) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
        
        Caso c = obtenerCasoPorIUE(usuarioActual, iUE);

		s.getTransaction().commit();
		
		if(c == null) {
			return "not found";	
		} else {
	        s.beginTransaction();
	        
	        eliminarInvolucradosPorCaso(usuarioActual, c.getId());
	        eliminarMensajesPorCaso(usuarioActual, c.getId());
	        
	        s.delete(c); 
	        
			s.getTransaction().commit();
			
			return "completado";
		}	
	}
	
	public static String eliminarInvolucradosPorCaso(String usuarioActual, long idCaso) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();

        Query query = s.createQuery("from Involucrado where idCaso = :idCaso");
        query.setParameter("idCaso", idCaso);
        List list = query.list();
        		
		s.getTransaction().commit();
		
		if(list.isEmpty()) {
			return "not found";	
		} else {
	        s.beginTransaction();
	        
	        for(Object o : list){
		        s.delete((Involucrado)o); 
	        }
	        
			s.getTransaction().commit();
			
			return "completado";
		}	
	}
	
	public static String eliminarMensajesPorCaso(String usuarioActual, long idCaso) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();

        Query query = s.createQuery("from Mensaje where idCaso = :idCaso");
        query.setParameter("idCaso", idCaso);
        List list = query.list();
        		
		s.getTransaction().commit();
		
		if(list.isEmpty()) {
			return "not found";	
		} else {
	        s.beginTransaction();
	        
	        for(Object o : list){
		        s.delete((Mensaje)o); 
	        }
	        
			s.getTransaction().commit();
			
			return "completado";
		}	
	}
	
	public static String eliminarInvolucrado(String usuarioActual, String iUE, String ciInvolucrado) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
        
        Caso c = obtenerCasoPorIUE(usuarioActual, iUE);

        Query query = s.createQuery("from Involucrado where IUE = :iUE and cedula = :ciInvolucrado");
        query.setParameter("iUE", iUE);
        query.setParameter("ciInvolucrado", ciInvolucrado);
        List list = query.list();
        Involucrado i = (Involucrado)list.get(0);
        		
		s.getTransaction().commit();
		
		if(i == null) {
			return "not found";	
		} else {
	        s.beginTransaction();
	        
	        s.delete(i); 
	        
			s.getTransaction().commit();
			
			return "completado";
		}	
	}
	//FIN SECCION BAJAS
}
