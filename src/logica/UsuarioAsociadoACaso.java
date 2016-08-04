package logica;

import javax.persistence.*;

@Entity
public class UsuarioAsociadoACaso {
	
	private long id;
	private long idUsuario;
	private long idCaso;
	private String tipo;
	
	protected UsuarioAsociadoACaso() {}
	
	public UsuarioAsociadoACaso(long idUsuario, long idCaso, String tipo) {
		this.idUsuario = idUsuario;
		this.idCaso = idCaso;
		this.tipo = tipo;
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
	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Basic
	public long getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(long idCaso) {
		this.idCaso = idCaso;
	}

	@Basic
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
