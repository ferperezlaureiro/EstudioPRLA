package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "involucrado")
public class Involucrado implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long id;
	private long idCaso;
	private String fechaDeNacimiento;
	private String nombre;
	private String cedula;
	private String nacionalidad;
	private String direccion;
	private String clase;
	
	protected Involucrado() {}

	public Involucrado(long idCaso, String fechaDeNacimiento, String nombre, String cedula, String nacionalidad, String direccion,
			String clase) {
		super();
		this.idCaso = idCaso;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nombre = nombre;
		this.cedula = cedula;
		this.nacionalidad = nacionalidad;
		this.direccion = direccion;
		this.clase = clase;
	}
	

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	@XmlElement
	private void setId(long id) {
		this.id = id;
	}
	
	@Basic
	public long getIdCaso() {
		return idCaso;
	}
	@XmlElement
	public void setIdCaso(long idCaso) {
		this.idCaso = idCaso;
	}

	@Basic
	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	@XmlElement
	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
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
	public String getNacionalidad() {
		return nacionalidad;
	}
	@XmlElement
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	@Basic
	public String getDireccion() {
		return direccion;
	}
	@XmlElement
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Basic
	public String getClase() {
		return clase;
	}
	@XmlElement
	public void setClase(String clase) {
		this.clase = clase;
	}
}
