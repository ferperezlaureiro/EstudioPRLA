package logica;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "mensaje")
public class Mensaje implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long id;
	private long idCaso;
	private long idUsuario;
	private String fecha;
	private String hora;
	private String contenido;
	private boolean infoMensaje;
	
	protected Mensaje() {}
	
	public Mensaje(long idCaso, long idUsuario, String fecha, String hora, String contenido, boolean infoMesaje) {
		super();
		this.idCaso = idCaso;
		this.idUsuario = idUsuario;
		this.fecha = fecha;
		this.hora = hora;
		this.contenido = contenido;
		this.infoMensaje = infoMesaje;
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
	public long getIdUsuario() {
		return idUsuario;
	}
	@XmlElement
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
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
	public String getHora() {
		return hora;
	}
	@XmlElement
	public void setHora(String hora) {
		this.hora = hora;
	}

	@Column(columnDefinition="TEXT")
	public String getContenido() {
		return contenido;
	}
	@XmlElement
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	@Basic
	public boolean getInfoMensaje() {
		return infoMensaje;
	}
	@XmlElement
	public void setInfoMensaje(boolean infoMensaje) {
		this.infoMensaje = infoMensaje;
	}
}
