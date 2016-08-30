package logica;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "permiso")
public class Permiso {
	private long id;
	private String code;
	private String nombre;
	
	protected Permiso(){}
	
	public Permiso(String nombre, String code) {
		this.nombre = nombre;
		this.code = code;
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
	public String getNombre() {
		return nombre;
	}
	@XmlElement
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Basic
	public String getCode() {
		return code;
	}
	@XmlElement
	public void setCode(String code) {
		this.code = code;
	}
}
