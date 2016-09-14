package logica;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "caso")
public class Caso  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String IUE;
	private String juzgado;
	private int turno;
	private String caratulado;
	private boolean suscrito;
	
	protected Caso() {}

	public Caso(String iUE, String juzgado, int turno, String caratulado, boolean suscrito) {
		this.IUE = iUE;
		this.juzgado = juzgado;
		this.turno = turno;
		this.caratulado = caratulado;
		this.suscrito = suscrito;
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
	@Column(name = "IUE",unique=true)
	public String getIUE() {
		return IUE;
	}
	@XmlElement
	public void setIUE(String iUE) {
		IUE = iUE;
	}

	@Basic
	public String getJuzgado() {
		return juzgado;
	}
	@XmlElement
	public void setJuzgado(String juzgado) {
		this.juzgado = juzgado;
	}

	@Basic
	public int getTurno() {
		return turno;
	}
	@XmlElement
	public void setTurno(int turno) {
		this.turno = turno;
	}

	@Basic
	public String getCaratulado() {
		return caratulado;
	}
	@XmlElement
	public void setCaratulado(String caratulado) {
		this.caratulado = caratulado;
	}

	@Basic
	public boolean getSuscrito() {
		return suscrito;
	}
	@XmlElement
	public void setSuscrito(boolean suscrito) {
		this.suscrito = suscrito;
	}
}
