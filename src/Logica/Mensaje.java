package Logica;

import java.sql.Date;

import javax.persistence.*;

@Entity
public class Mensaje {
	private long id;
	private long idCaso;
	private long idUsuario;
	private Date fecha;
	private String contenido;
	
	public Mensaje() {}
	
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
	public void setId(long id) {
		this.id = id;
	}
	
	@Basic
	public long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(long idCaso) {
		this.idCaso = idCaso;
	}
	
	@Basic
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Temporal(TemporalType.DATE)
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Basic
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
}
