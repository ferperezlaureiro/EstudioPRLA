package logica;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Involucrado {
	
	private long id;
	private long idCaso;
	private Date fechaDeNacimiento;
	private String nombre;
	private String cedula;
	private String nacionalidad;
	private String direccion;
	private String clase;
	
	protected Involucrado() {}

	public Involucrado(long idCaso, Date fechaDeNacimiento, String nombre, String cedula, String nacionalidad, String direccion,
			String clase) {
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
	private void setId(long id) {
		this.id = id;
	}
	
	@Basic
	public long getIdCaso() {
		return idCaso;
	}
	
	public void setIdCaso(long idCaso) {
		this.idCaso = idCaso;
	}

	@Temporal(TemporalType.DATE)
	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	
	@Basic
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Basic
	public String getCedula() {
		return cedula;
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	@Basic
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	@Basic
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Basic
	public String getClase() {
		return clase;
	}
	
	public void setClase(String clase) {
		this.clase = clase;
	}
}
