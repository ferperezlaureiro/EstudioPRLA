package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
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
	
	protected Usuario() {}
	
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
	}
	


	@Id
	@GeneratedValue
	public long getId()
    {
        return id;
    }
	@XmlElement
	private void setId(long id) {
		this.id = id;
	}
	
	@Basic
	@Column(name = "usuario",unique=true)
	public String getUsuario() {
		return usuario;
	}
	@XmlElement
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	@Basic
	public String getContrasenia() {
		return contrasenia;
	}
	@XmlElement
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	@Basic
	public String getNombre() {
		return nombre;
	}
	@XmlElement
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Basic
	public String getCedula() {
		return cedula;
	}
	@XmlElement
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	@Basic
	public String getEmail() {
		return email;
	}
	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	public String getTel() {
		return tel;
	}
	@XmlElement
	public void setTel(String tel) {
		this.tel = tel;
	}

	@Basic
	public String getCel() {
		return cel;
	}
	@XmlElement
	public void setCel(String cel) {
		this.cel = cel;
	}
	
	@Basic
	public String getDomicilio() {
		return domicilio;
	}
	@XmlElement
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	@Basic
	public String getDomicilioLaboral() {
		return domicilioLaboral;
	}
	@XmlElement
	public void setDomicilioLaboral(String domicilioLaboral) {
		this.domicilioLaboral = domicilioLaboral;
	}

	@Basic
	public String getRut() {
		return rut;
	}
	@XmlElement
	public void setRut(String rut) {
		this.rut = rut;
	}

	@Temporal(TemporalType.DATE)
	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	@XmlElement
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	
}
