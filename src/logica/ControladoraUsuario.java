package logica;
import java.sql.Date;
import java.util.ArrayList;


import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

public class ControladoraUsuario {
	public ControladoraUsuario() {
        BasicConfigurator.configure();
        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
        cargarUsuarioPrueba();
	}
	
	//PRINCIPIO SECCION CONSULTAS
	public static boolean existeUsuario(String usuario) {
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();

        Query query = s.createQuery("from Usuario where usuario = :usuario ");
        query.setParameter("usuario", usuario);
        List list = query.list();
        s.disconnect();
        
        if (list.isEmpty()){
        	return false;
        }
        
        Usuario usr = (Usuario)list.get(0);
		
		if(usr == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public static Usuario buscarUsuario(String usuario) {
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
		
        Query query = s.createQuery("from Usuario where usuario = :usuario ");
        query.setParameter("usuario", usuario);
        List list = query.list();
        s.disconnect();
        
        if(list.isEmpty()){
        	return null;
        }
        
        Usuario usr = (Usuario)list.get(0);
		
		return usr;
	}
	
	public static String login(String usuario, String contrasenia) {
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
        
        Usuario usr = buscarUsuario(usuario);

		s.getTransaction().commit();
        s.disconnect();
		
		String retorno = "usuario";
		
		if (usr != null) {
			if (usr.getContrasenia().equals(contrasenia)) {
				retorno = Utilidades.Encriptar(usr.getUsuario() + "-" + usr.getCedula() + "-" + usr.getContrasenia());
			} else {
				retorno = "contrasenia";
			}
		}
		
		return retorno;
	}
	//FIN SECCION CONSULTAS

	//PRINCIPIO SECCION ALTAS
	public static void cargarUsuarioPrueba() {
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
	
	public static void AgregarUsuario(String usuarioActual, String usuario, String contrasenia, String nombre, String cedula, String email, 
			String tel, String cel, String domicilio, String domicilioLaboral, String rut, Date fechaDeNacimiento) throws Exception {
        //Se valida que la sesion sea valida
		validateUsrSession(usuarioActual);
		
		//Se valida que los datos sean validos
		validarDatosUsuario(usuario, contrasenia, nombre, cedula, email, tel, cel, domicilio, domicilioLaboral);
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
		
        //Se ingresa un nuevo usuario
		Usuario u = new Usuario (usuario, contrasenia, nombre, cedula, email, tel, cel, domicilio, domicilioLaboral, rut, fechaDeNacimiento);
		
		//Se guarda el nuevo usuario en la base de datos
		s.save(u);
		s.getTransaction().commit();
        s.disconnect();
	}
	
	private static void validarDatosUsuario (String usuario, String contrasenia, String nombre, String cedula, String email, String tel, String cel, 
			String domicilio, String domicilioLaboral) throws Exception {
		String errores = "";
		if(!Validacion.validarUsuario(usuario))
			errores += "usuario";
		if(!Validacion.validarContrasenia(contrasenia))
			errores += "|contrasenia";
		if(!Validacion.validarNombre(nombre))
			errores += "|nombre";
		if(!Validacion.validarCedula(cedula))
			errores += "|cedula";
		if(!Validacion.validarEmail(email))
			errores += "|email";
		if(!Validacion.validarTel(tel))
			errores += "|tel";
		if(!Validacion.validarCel(cel))
			errores += "|cel";
		if(!Validacion.validarDomicilio(domicilio))
			errores += "|domicilio";
		if(!Validacion.validarDomicilio(domicilioLaboral))
			errores += "|domicilioLaboral";
		
		if(errores != "")
			throw new Exception(errores);
	}
	
	public static void validateUsrSession(String usuarioActual) throws Exception {
		String usuarioActualDesencriptado = Utilidades.Desencriptar(usuarioActual);
		String[] parts = usuarioActualDesencriptado.split("-"); 
		String result = login(parts[0], parts[2]);
		if (result == "usuario" || result == "contrasenia")
			throw new Exception("Session error");
	}
	//FIN SECCION ALTAS
	
	//PRINCIPIO SECCION BAJAS
	public static String eliminarUsuario(String usuarioActual, String usuario) throws Exception {
		
		//Se valida que la sesion sea valida
		validateUsrSession(usuarioActual);
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
        
        Usuario usr = buscarUsuario(usuario);

		s.getTransaction().commit();
        s.disconnect();
		
		if(usr == null) {
			return "not found";	
		} else {
	        s.beginTransaction();
	        
	        s.delete(usr); 
	        
			s.getTransaction().commit();
	        s.disconnect();
			
			return "completado";
		}	
	}
	//FIN SECCION BAJAS
}
