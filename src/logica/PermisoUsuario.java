package logica;

import javax.persistence.*;

@Entity
public class PermisoUsuario {
	
	private long id;
	private long idPermiso;
	private long idUsuario;
	
	protected PermisoUsuario() {}

	public PermisoUsuario(long idPermiso, long idUsuario) {
		this.idPermiso = idPermiso;
		this.idUsuario = idUsuario;
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
	public long getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(long idPermiso) {
		this.idPermiso = idPermiso;
	}

	@Basic
	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

}
