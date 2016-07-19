package logica;

import java.sql.Date;

public class Fachada {
	private ControladoraUsuario controladoraUsuario;
	
	private static Fachada instancia;
	
	
	private Fachada(){
		controladoraUsuario = new ControladoraUsuario();
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
			if(controladoraUsuario.existeUsuario(usuario))
				return "duplicado";
			controladoraUsuario.AgregarUsuario(usrKey, usuario, contrasenia, nombre, cedula, email, tel, cel, domicilio, domicilioLaboral, rut, fechaDeNacimiento);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "completado";
	}
	
	public boolean existeUsuario(String usuario) {
		return controladoraUsuario.existeUsuario(usuario);
	}
	
	public Usuario buscarUsuario(String usuario) {
		return controladoraUsuario.buscarUsuario(usuario);
	}
	
	public String login(String usuario, String contrasenia) {
		return controladoraUsuario.login(usuario, contrasenia);
	}
	
	public String eliminarUsuario(String usrKey, String usuario) {
		
		try {
			return controladoraUsuario.eliminarUsuario(usrKey, usuario);
			
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	// CONTROLADORA USUARIO
}
