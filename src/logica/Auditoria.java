package logica;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "auditoria")
public class Auditoria implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
	private String hora;
	private String fecha;
	private String accion;
	private long idUsuario;
	
	protected Auditoria() {}
	
	public Auditoria(String hora, String fecha, String accion, long idUsuario){
		this.hora = hora;
		this.fecha = fecha;
		this.accion = accion;
		this.idUsuario = idUsuario;
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
	public String getHora() {
		return hora;
	}
	@XmlElement
	public void setHora(String hora) {
		this.hora = hora;
	}

	@Basic
	public String getFecha() {
		return fecha;
	}
	@XmlElement
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Basic
	public String getAccion() {
		return accion;
	}
	@XmlElement
	public void setAccion(String accion) {
		this.accion = accion;
	}

	@Basic
	public long getIdUsuario() {
		return idUsuario;
	}
	@XmlElement
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
}
