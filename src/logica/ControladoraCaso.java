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
		
		//Se valida el formato del IUE
		if (!Validacion.validarIUE(iUE))
			throw new Exception("IUE");
			
		Session s = HibernateUtil.getSession();
		
        Query query = s.createQuery("from Caso where IUE = :iUE ");
        query.setParameter("iUE", iUE);
        List list = query.list();

        s.disconnect();
        
        if (!list.isEmpty())
        	return (Caso)list.get(0);
        else
        	return null;
	}
	
	public static boolean existeCaso (String usuarioActual, String iUE) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
				
		Session s = HibernateUtil.getSession();
		
        Query query = s.createQuery("from Caso where IUE = :iUE ");
        query.setParameter("iUE", iUE);

        s.disconnect();
        
        if (!query.list().isEmpty())
        	return true;
        else
        	return false;
	}
	
	public static ArrayList<Mensaje> obtenerConversacion (String usuarioActual, String iUE) throws Exception {
		ArrayList<Mensaje> mensajes = new ArrayList<>();
		
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se valida el formato del IUE
		if (!Validacion.validarIUE(iUE))
			throw new Exception("IUE");
        
        Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
        if(c != null) {
    		Session s = HibernateUtil.getSession();
    		
        	Query query = s.createQuery("from Mensaje where idCaso = :idCaso ");
            query.setParameter("idCaso", c.getId());
            List list = query.list();
            
            s.disconnect();
            
            if(!list.isEmpty()) {
            	for(Object o: list) {
            		mensajes.add((Mensaje)o);
            	}
            }
        }
		
        return mensajes;
	}
	
	public static ArrayList<Involucrado> obtenerInvolucrados (String usuarioActual, String iUE) throws Exception {
		ArrayList<Involucrado> involucrados = new ArrayList<>();
        
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se valida el formato del IUE
		if (!Validacion.validarIUE(iUE))
			throw new Exception("IUE");
		
		Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
        
		if(c != null) {
        	Session s = HibernateUtil.getSession();
    		
            Query query = s.createQuery("from Involucrado where idCaso = :idCaso ");
            query.setParameter("idCaso", c.getId());
            List list = query.list();

            s.disconnect();
            
            if(!list.isEmpty()) {
            	for(Object o: list) {
            		involucrados.add((Involucrado)o);
            	}
            }
        }

        return involucrados;
	}
	
	public static boolean existeInvolucrado (String usuarioActual, String iUE, String cedula) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);

		Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
        
		if (c != null) {
			Session s = HibernateUtil.getSession();
    		
            Query query = s.createQuery("from Involucrado where idCaso = :idCaso and cedula = :cedula");
            query.setParameter("idCaso", c.getId());
            query.setParameter("cedula", cedula);
            List list = query.list();

            s.disconnect();

            Involucrado i = null;
            
            if (!list.isEmpty())
            	i = (Involucrado)list.get(0);
            
            return (i != null);
		}
		
		return false;
	}
	//FIN SECCION CONSULTAS
	
	//PRINCIPIO SECCION INSERCIONES
	public static void agregarCaso(String usuarioActual, String iUE, String juzgado, int turno, String caratulado) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se valida que los datos sean validos
		validarDatosCaso(iUE, juzgado, turno, caratulado);
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();

        Caso c = new Caso(iUE, juzgado, turno, caratulado);
		
		//Se guarda el nuevo caso en la base de datos
		s.save(c);
		s.getTransaction().commit();

        s.disconnect();
	}
	
	private static void validarDatosCaso(String iUE, String juzgado, int turno, String caratulado) throws Exception{
		String errores = "";
		if(!Validacion.validarIUE(iUE))
			errores += "IUE";
		if(!Validacion.validarDosPalabras(juzgado))
			errores += "|juzgado";
		if(!Validacion.validarTurno(turno))
			errores += "|turno";
		if(!Validacion.validarCaratulado(caratulado))
			errores += "|caratulado";
		if(errores != "") {
			throw new Exception(errores);
		}
	}
	
	public static void agregarInvolucrado(String usuarioActual, String iUE, Date fechaDeNacimiento, String nombre, String cedula, 
			String nacionalidad, String domicilio,String clase) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se valida que los datos sean validos
		validarDatosInvolucrado(iUE, fechaDeNacimiento, nombre, cedula, nacionalidad, domicilio, clase);
		
		Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
		
		if(c != null) {
			//Se obtiene y empieza la session
			Session s = HibernateUtil.getSession();
	        s.beginTransaction();

	        Involucrado i = new Involucrado(c.getId(), fechaDeNacimiento, nombre, cedula, nacionalidad, domicilio, clase);
			
			//Se guarda el nuevo involucrado en la base de datos
			s.save(i);
			
			s.getTransaction().commit();	

	        s.disconnect();	
		}
	}
	
	public static void validarDatosInvolucrado(String iUE, Date fechaDeNacimiento, String nombre, String cedula, 
			String nacionalidad, String domicilio,String clase) throws Exception {
		String errores = "";
		if(!Validacion.validarIUE(iUE))
			errores += "IUE";
		if(!Validacion.validarFechaDeNacimiento(fechaDeNacimiento))
			errores += "|fechaDeNacimiento";
		if(!Validacion.validarNombre(nombre))
			errores += "|nombre";
		if(!Validacion.validarCedula(cedula))
			errores += "|cedula";
		if(!Validacion.validarDosPalabras(nacionalidad))
			errores += "|nacionalidad";
		if(!Validacion.validarDomicilio(domicilio))
			errores += "|domicilio";
		if(!Validacion.validarDosPalabras(clase))
			errores += "|clase";
		
		if (errores != "") {
			throw new Exception(errores);
		}
	}
	
	public static void agregarMensaje(String usuarioActual, String iUE,  String usuario, Date fecha, String contenido) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
		
		validarDatosMensaje(iUE, usuario, fecha, contenido);
		
		if (!ControladoraUsuario.existeUsuario(usuario))
			throw new Exception("usuario");
		
		Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
		
		if(c != null) {
			//Se obtiene y empieza la session
			Session s = HibernateUtil.getSession();
	        s.beginTransaction();

	        Mensaje m = new Mensaje(c.getId(), ControladoraUsuario.buscarUsuario(usuario).getId(), fecha, contenido);
			
			//Se guarda el nuevo involucrado en la base de datos
			s.save(m);
			s.getTransaction().commit();	
			
			s.disconnect();
		}	
	}
	
	public static void validarDatosMensaje (String iUE,  String usuario, Date fecha, String contenido) throws Exception {
		String errores = "";
		if(!Validacion.validarIUE(iUE))
			errores += "IUE";
		if(!Validacion.validarUsuario(usuario))
			errores += "|usuario";
		if(!Validacion.validarFechaDeNacimiento(fecha))
			errores += "|fecha";
		if(!Validacion.validarContenidoMensaje(contenido))
			errores += "|contenido";
		
		if (errores != "") {
			throw new Exception (errores);
		}
	}
	//FIN SECCION INSERCIONES
	
	//PRINCIPIO SECCION BAJAS
	public static String eliminarCaso(String usuarioActual, String iUE) {
		Caso c;
		try {
			//Se valida que la sesion sea valida
			ControladoraUsuario.validateUsrSession(usuarioActual);
	        
	        c = obtenerCasoPorIUE(usuarioActual, iUE);
		} catch (Exception e) {
			return e.getMessage();
		}
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
		
		if(c == null) {
			return "not found";	
		} else {
	        s.beginTransaction();
	        
	        eliminarInvolucradosPorCaso(usuarioActual, c.getId());
	        eliminarMensajesPorCaso(usuarioActual, c.getId());
	        
	        s.delete(c); 
	        
			s.getTransaction().commit();
			
			s.disconnect();
			
			return "completado";
		}	
	}
	
	private static String eliminarInvolucradosPorCaso(String usuarioActual, long idCaso) {
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();

        Query query = s.createQuery("from Involucrado where idCaso = :idCaso");
        query.setParameter("idCaso", idCaso);
        List list = query.list();
		
		if(list.isEmpty()) {
			return "not found";	
		} else {
	        
	        for(Object o : list){
		        s.delete((Involucrado)o); 
	        }
			
			return "completado";
		}	
	}
	
	private static String eliminarMensajesPorCaso(String usuarioActual, long idCaso) {
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();

        Query query = s.createQuery("from Mensaje where idCaso = :idCaso");
        query.setParameter("idCaso", idCaso);
        List list = query.list();
		
		if(list.isEmpty()) {
			return "not found";	
		} else {
	        for(Object o : list){
		        s.delete((Mensaje)o); 
	        }
			
			return "completado";
		}	
	}
	
	public static String eliminarInvolucrado(String usuarioActual, String iUE, String ciInvolucrado) {
		Caso c;
		
		try {
			//Se valida que la sesion sea valida
			ControladoraUsuario.validateUsrSession(usuarioActual);
	        
	        c = obtenerCasoPorIUE(usuarioActual, iUE);
		} catch (Exception e) {
			return e.getMessage();
		}
		
        if(c != null) {
    		//Se obtiene y empieza la session
    		Session s = HibernateUtil.getSession();
    		
            Query query = s.createQuery("from Involucrado where idCaso = :idCaso and cedula = :ciInvolucrado");
            query.setParameter("idCaso", c.getId());
            query.setParameter("ciInvolucrado", ciInvolucrado);
            List list = query.list();
            Involucrado i = (Involucrado)list.get(0);
    		
    		if(i == null) {
    			return "not found";	
    		} else {
    	        s.beginTransaction();
    	        
    	        s.delete(i); 
    	        
    			s.getTransaction().commit();
    			
    			s.disconnect();
    			
    			return "completado";
    		}	
        } else {
        	return "not found";
        }
	}
	//FIN SECCION BAJAS
}
