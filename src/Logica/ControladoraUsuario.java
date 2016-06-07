package Logica;
import java.sql.Date;
import java.util.ArrayList;

public class ControladoraUsuario {

	private ArrayList<Usuario> usuarios;
	private ArrayList<Permiso> permisos;
	
	public ControladoraUsuario(){
		usuarios = new ArrayList<Usuario>();
		permisos = new ArrayList<Permiso>();
		cargarPermisos();
	}
	
	public void cargarPermisos() {
		Permiso p = new Permiso ("p1", "Agregar usuario");
		permisos.add(p);
		p = new Permiso ("p2", "Eliminar usuario");
		permisos.add(p);
		p = new Permiso ("p3", "Alta usuario");
		permisos.add(p);
	}
	
	private void validarDatosUsuario (String usuario, String contrasenia, String nombre, String cedula, String email, String tel, String cel, 
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
	
	private void validateUsrSession(String usuarioActual) throws Exception {
		String usuarioActualDesencriptado = Utilidades.Desencriptar(usuarioActual);
		String[] parts = usuarioActualDesencriptado.split("-"); 
		String result = login(parts[0], parts[1]);
		if (result == "usuario" || result == "contrasenia")
			throw new Exception("Session error");
	}
	
	public void AgregarUsuario(String usuario, String contrasenia, String nombre, String cedula, String email, 
			String tel, String cel, String domicilio, String domicilioLaboral, String rut, Date fechaDeNacimiento) throws Exception {
		
		
		validarDatosUsuario(usuario, contrasenia, nombre, cedula, email, tel, cel, domicilio, domicilioLaboral);
		
		Usuario u = new Usuario (usuario, contrasenia, nombre, cedula, email, tel, cel, domicilio, domicilioLaboral, rut, fechaDeNacimiento);
		usuarios.add(u);
	}
	
	public boolean existeUsuario(String usuario) {
		
		for (Usuario u: usuarios) {
			if (u.getUsuario().equals(usuario)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Usuario buscarUsuario(String usuario) {
		
		for (Usuario u: usuarios) {
			if (u.getUsuario().equals(usuario)) {
				return u;
			}
		}
		
		return null;
	}
	
	public String login(String usuario, String contrasenia) {
		String retorno = "usuario";
		
		for (Usuario u: usuarios) {
			if (u.getUsuario().equals(usuario)) {
				if (u.getContrasenia().equals(contrasenia)) {
					retorno = Utilidades.Encriptar(u.getUsuario() + "-" + u.getCedula() + "-" + u.getContrasenia());
				} else {
					retorno = "contrasenia";
				}
			}
		}
		
		return retorno;
	}
	
	public void eliminarUsuario(String usuarioActual, String usuario) {
		Usuario aux = null;
		
		for (Usuario u: usuarios) {
			if (u.getUsuario().equals(usuario)) {
				aux = u;
			}
		}
		
		usuarios.remove(aux);		
	}
}
