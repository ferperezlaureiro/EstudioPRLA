package Logica;

import java.sql.Date;
import java.util.ArrayList;

public class Usuario {

	
	private String usuario;
	private String contrasenia;
	private String nombre;
	private String cedula;
	private String email;
	private String tel;
	private String cel;
	private String domicilio;
	private String domicilioLaboral;
	private String rut;
	private Date fechaDeNacimiento;
	
	private ArrayList<Permiso> permisos;

	public Usuario(String usuario, String contrasenia, String nombre, String cedula, String email, String tel, String cel,
			String domicilio, String domicilioLaboral, String rut, Date fechaDeNacimiento) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.nombre = nombre;
		this.cedula = cedula;
		this.email = email;
		this.tel = tel;
		this.cel = cel;
		this.domicilio = domicilio;
		this.domicilioLaboral = domicilioLaboral;
		this.rut = rut;
		this.fechaDeNacimiento = fechaDeNacimiento;
		permisos = new ArrayList<Permiso>();
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCel() {
		return cel;
	}

	public void setCel(String cel) {
		this.cel = cel;
	}
	
	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getDomicilioLaboral() {
		return domicilioLaboral;
	}

	public void setDomicilioLaboral(String domicilioLaboral) {
		this.domicilioLaboral = domicilioLaboral;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	
	public void agragerPermiso(Permiso permiso) {
		permisos.add(permiso);
	}
	
	public boolean tienePermiso(Permiso permiso) {
		for(Permiso p: permisos) {
			if (p.equals(permiso)) {
				return true;
			}
		}
		return false;
	}
	
}
