package logica;

import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "mensaje")
public class Mensaje {
	private long id;
	private long idCaso;
	private long idUsuario;
	private Date fecha;
	private String contenido;
	
	protected Mensaje() {}
	
	public Mensaje(long idCaso, long idUsuario, Date fecha, String contenido) {
		super();
		this.idCaso = idCaso;
		this.idUsuario = idUsuario;
		this.fecha = fecha;
		this.contenido = contenido;
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

	@Temporal(TemporalType.DATE)
	public Date getFecha() {
		return fecha;
	}
	@XmlElement
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Basic
	public String getContenido() {
		return contenido;
	}
	@XmlElement
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
}
