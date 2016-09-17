package logica;

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
	@SuppressWarnings("finally")
	public ArrayList<Usuario> obtenerUsuarios(String usrKey) {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			usuarios = ControladoraUsuario.obtenerUsuarios(usrKey);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return usuarios;
		}
	}
	
	public String AgregarUsuario(String usrKey, String usuario, String contrasenia, String nombre, String cedula, String email, String tel, 
			String cel, String domicilio, String domicilioLaboral, String rut, String fechaDeNacimiento) {
		try {
			if(ControladoraUsuario.existeUsuario(usuario))
				return "duplicado";
			ControladoraUsuario.AgregarUsuario(usrKey, usuario, contrasenia, nombre, cedula, email, tel, cel, domicilio, domicilioLaboral, rut, fechaDeNacimiento);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}
	
	public String ModificarUsuario(String usrKey, String usuarioUsado, String usuario, String contrasenia, String nombre, String cedula, 
			String email, String tel, String cel, String domicilio, String domicilioLaboral, String rut, String fechaDeNacimiento) {
		try {
			ControladoraUsuario.modificarUsuario(usrKey, usuarioUsado, usuario, contrasenia, nombre, cedula, email, tel, cel, domicilio, domicilioLaboral, rut, fechaDeNacimiento);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}
	
	public String login(String usuario, String contrasenia) {
		try {
			return ControladoraUsuario.login(usuario, contrasenia);
		} catch (Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public String eliminarUsuario(String usrKey, String usuario) {
		
		try {
			return ControladoraUsuario.eliminarUsuario(usrKey, usuario);
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public Usuario obtenerUsuario(String usrKey, String usuario) {
		try {
			return ControladoraUsuario.buscarUsuario(usrKey, usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	// CONTROLADORA USUARIO
	
	// CONTROLADORA CASO
	public String agregarCaso (String usuarioActual, String iUE, String juzgado, int turno, String caratulado, boolean suscrito) {
		try {
			if (ControladoraCaso.existeCaso(usuarioActual, iUE))
				return "duplicado";
			ControladoraCaso.agregarCaso(usuarioActual, iUE, juzgado, turno, caratulado, suscrito);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}
	
	public String modificarCaso (String usuarioActual, String iUEUsado, String iUE, String juzgado, int turno, String caratulado, boolean suscrito) {
		try {
			ControladoraCaso.modificarCaso(usuarioActual, iUEUsado, iUE, juzgado, turno, caratulado, suscrito);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}
	
	public String asociarUsuarioACaso (String usuarioActual, String usuario, String iUE, String tipo) {
		try {
			ControladoraCaso.asociarUsuarioACaso(usuarioActual, usuario, iUE, tipo);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}

	public String desasociarUsuarioACaso (String usuarioActual, String usuario, String iUE) {
		try {
			ControladoraCaso.desasociarUsuarioACaso(usuarioActual, usuario, iUE);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}
	
	public String agregarInvolucrado (String usuarioActual, String iUE, String fechaDeNacimiento, String nombre, String cedula, 
			String nacionalidad, String domicilio, String clase) {
		try {
			if (ControladoraCaso.obtenerInvolucrado(usuarioActual, iUE, cedula) != null)
				return "duplicado";
			ControladoraCaso.agregarInvolucrado(usuarioActual, iUE, fechaDeNacimiento, nombre, cedula, nacionalidad, domicilio, clase);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}
	
	public String modificarInvolucrado (String usuarioActual, String iUE, String cedulaUsada, String fechaDeNacimiento, String nombre, String cedula, 
			String nacionalidad, String domicilio, String clase) {
		try {
			ControladoraCaso.modificarInvolucrado(usuarioActual, iUE, cedulaUsada, fechaDeNacimiento, nombre, cedula, nacionalidad, domicilio, clase);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}
	
	public String agregarMensaje (String usuarioActual, String iUE, String contenido) {
		try {
			ControladoraCaso.agregarMensaje(usuarioActual, iUE, contenido, false);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}
	
	@SuppressWarnings("finally")
	public ArrayList<Caso> obtenerCasos (String usuarioActual) {
		ArrayList<Caso> casos = null;
		try {
			casos = ControladoraCaso.obtenerCasos(usuarioActual);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return casos;
		}
	}
	
	@SuppressWarnings("finally")
	public ArrayList<Caso> obtenerCasosPorUsuario (String usuarioActual, String usuario) {
		ArrayList<Caso> casos = null;
		try {
			casos = ControladoraCaso.obtenerCasosPorUsuario(usuarioActual, usuario);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return casos;
		}
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
		try {
			ControladoraCaso.eliminarCaso(usuarioActual, iUE);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}
	
	public String eliminarInvolucrado(String usuarioActual, String iUE, String ciInvolucrado) {
		try {
			ControladoraCaso.eliminarInvolucrado(usuarioActual, iUE, ciInvolucrado);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}
	// CONTROLADORA CASO
}
