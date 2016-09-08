package logica;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacion {

	public static boolean validarCedula(String cedula) {
		if (cedula == null || cedula == "")
			return false;
		String regex = "(^[1-9]{1}+[0-9]{7}$)";
		Pattern patron = Pattern.compile(regex);
		Matcher emparejador = patron.matcher(cedula);
		return emparejador.matches();
	}
	
	public static boolean validarTel(String tel) {
		if (tel == null || tel == "")		
			return false;
		String regex = "(^[1-9]{1}+[0-9]{7}$)";
		Pattern patron = Pattern.compile(regex);
		Matcher emparejador = patron.matcher(tel);
		return emparejador.matches();
	}
	
	public static boolean validarCel(String cel) {
		if (cel == null || cel == "")		
			return false;
		String regex = "^09[0-9]{7}$";
		Pattern patron = Pattern.compile(regex);
		Matcher emparejador = patron.matcher(cel);
		return emparejador.matches();
	}
	
	public static boolean validarEmail(String email) {
		if (email == null || email == "")
			return false;
		String regex = "[a-zA-Z0-9._%-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,4}";
		Pattern patron = Pattern.compile(regex);
		Matcher emparejador = patron.matcher(email);
		return emparejador.matches();
	}
	
	public static boolean validarContrasenia(String contrasenia) {
		if (contrasenia == null || contrasenia == "")
			return false;
		String regex = "(?=.*\\d)(?=.*[\u0021-\u002b\u003c-\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{6,16}";
		Pattern patron = Pattern.compile(regex);
		Matcher emparejador = patron.matcher(contrasenia);
		return emparejador.matches();
	}
	
	public static boolean validarUsuario(String usuario) {
		if (usuario == null || usuario == "")
			return false;
		String regex = "(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,15}";
		Pattern patron = Pattern.compile(regex);
		Matcher emparejador = patron.matcher(usuario);
		return emparejador.matches();
	}
	
	public static boolean validarNombre(String nombre) {
		if (nombre == null || nombre == "")
			return false;
		String regex = "^([a-zA-Z]*)(\\p{L}+)(([ ])(\\p{L}+))(([ ])(\\p{L}+))?(([ ])(\\p{L}+))?$";
		Pattern patron = Pattern.compile(regex);
		Matcher emparejador = patron.matcher(nombre);
		return emparejador.matches();
	}
	
	public static boolean validarDomicilio(String domicilio) {
		if (domicilio == null || domicilio == "")
			return true;
		String regex = "(\\p{L}+)(([ ])(\\p{L}+))?(([ ])(\\p{L}+))?(([ ])(\\p{L}+))?(([ ])([0-9]{4}))";
		Pattern patron = Pattern.compile(regex);
		Matcher emparejador = patron.matcher(domicilio);
		return emparejador.matches();
	}
	
	public static boolean validarFechaDeNacimiento (String fechaDeNacimiento) {
		if (fechaDeNacimiento == null ||fechaDeNacimiento == "")
			return false;
		String regex = "(([0-9]{2})+(/([0-9]{2}))+(/([1-9]{1}[0-9]{3})))";
		Pattern patron = Pattern.compile(regex);
		Matcher emparejador = patron.matcher(fechaDeNacimiento);
		return emparejador.matches();
	}
	
	public static boolean validarIUE(String IUE) {
		if (IUE == null || IUE == "")
			return true;
		String regex = "(([0-9]{1})+(-([0-9]{4}))+(/([1-9]{1}[0-9]{3})))";
		Pattern patron = Pattern.compile(regex);
		Matcher emparejador = patron.matcher(IUE);
		return emparejador.matches();
	}
	
	public static boolean validarDosPalabras (String dosPalabras) {
		if (dosPalabras == null || dosPalabras == "")
			return true;
		String regex = "(\\p{L}+)(([ ])(\\p{L}+))?";
		Pattern patron = Pattern.compile(regex);
		Matcher emparejador = patron.matcher(dosPalabras);
		return emparejador.matches();
	}
	
	public static boolean validarTurno(int turno) {
		if (turno > 0 && turno < 30) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean validarCaratulado (String caratulado) {
		if (caratulado == null || caratulado == "")
			return true;
		String regex = "(\\p{L}+)(([ ])(\\p{L}+))?(([ ])(\\p{L}+))?(([ ])(\\p{L}+))?(([ ])(\\p{L}+))?(([ ])(\\p{L}+))?(([ ])(\\p{L}+))?(([ ])(\\p{L}+))?(([ ])(\\p{L}+))?(([ ])(\\p{L}+))?";
		Pattern patron = Pattern.compile(regex);
		Matcher emparejador = patron.matcher(caratulado);
		return emparejador.matches();
	}
	
	public static boolean validarContenidoMensaje (String contenido) {
		if (contenido == null || contenido == "" || contenido.length() == 0 || contenido == " ")
			return false;
		return true;
	}
	
	public static boolean validarTipoUsuario (String tipo) {
		if (tipo == null || tipo == "")
			return false;
		if (tipo.equals("profecional") || tipo.equals("funcionario") || tipo.equals("cliente"))
			return true;
		return false;
	}
}
