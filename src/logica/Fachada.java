package logica;

import java.sql.Date;
import java.util.ArrayList;

public class Fachada {
	private static Fachada instancia;
	
	private Fachada(){
		new Configuracion();
	}
	
	
	// SINGLETON
	public static Fachada getInstancia(){
		if (instancia == null) {
			instancia = new Fachada();
		}
		return instancia;
	}
	// SINGLETON
	
	

	// CONTROLADORA USUARIO
	public String AgregarUsuario(String usrKey, String usuario, String contrasenia, String nombre, String cedula, String email, String tel, String cel,
			String domicilio, String domicilioLaboral, String rut, Date fechaDeNacimiento) {
		try {
			if(ControladoraUsuario.existeUsuario(usuario))
				return "duplicado";
			ControladoraUsuario.AgregarUsuario(usrKey, usuario, contrasenia, nombre, cedula, email, tel, cel, domicilio, domicilioLaboral, rut, fechaDeNacimiento);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "completado";
	}
	
	public String login(String usuario, String contrasenia) {
		return ControladoraUsuario.login(usuario, contrasenia);
	}
	
	public String eliminarUsuario(String usrKey, String usuario) {
		
		try {
			return ControladoraUsuario.eliminarUsuario(usrKey, usuario);
			
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	// CONTROLADORA USUARIO
	
	// CONTROLADORA CASO
	public String agregarCaso (String usuarioActual, String iUE, String juzgado, int turno, String caratulado) {
		try {
			if (ControladoraCaso.existeCaso(usuarioActual, iUE))
				return "duplicado";
			ControladoraCaso.agregarCaso(usuarioActual, iUE, juzgado, turno, caratulado);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "completado";
	}
	
	public String agregarInvolucrado (String usuarioActual, String iUE, Date fechaDeNacimiento, String nombre, String cedula, 
			String nacionalidad, String domicilio, String clase) {
		try {
			if (ControladoraCaso.existeInvolucrado(usuarioActual, iUE, cedula))
				return "duplicado";
			ControladoraCaso.agregarInvolucrado(usuarioActual, iUE, fechaDeNacimiento, nombre, cedula, nacionalidad, domicilio, clase);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "completado";
	}
	
	public String agregarMensaje (String usuarioActual, String iUE,  String usuario, Date fecha, String contenido) {
		try {
			ControladoraCaso.agregarMensaje(usuarioActual, iUE, usuario, fecha, contenido);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}
	
	@SuppressWarnings("finally")
	public Caso obtenerCasoPorIUE (String usuarioActual, String iUE) {
		Caso c = null;
		try {
			c = ControladoraCaso.obtenerCasoPorIUE(usuarioActual, iUE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return c;
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Mensaje> obtenerConversacion (String usuarioActual, String iUE) {
		ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
		try {
			mensajes = ControladoraCaso.obtenerConversacion(usuarioActual, iUE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return mensajes;
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Involucrado> obtenerInvolucrados (String usuarioActual, String iUE) {
		ArrayList<Involucrado> involucrados = new ArrayList<Involucrado>();
		try {
			involucrados = ControladoraCaso.obtenerInvolucrados(usuarioActual, iUE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return involucrados;
		}
	}
	
	public String eliminarCaso (String usuarioActual, String iUE) {
		return ControladoraCaso.eliminarCaso(usuarioActual, iUE);
	}
	
	public String eliminarInvolucrado(String usuarioActual, String iUE, String ciInvolucrado) {
		return ControladoraCaso.eliminarInvolucrado(usuarioActual, iUE, ciInvolucrado);
	}
	// CONTROLADORA CASO
}
