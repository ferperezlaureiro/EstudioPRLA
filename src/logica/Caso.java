package logica;

import javax.persistence.*;

@Entity
public class Caso {
	private long id;
	private String IUE;
	private String juzgado;
	private int turno;
	private String caratulado;
	
	protected Caso() {}

	public Caso(String iUE, String juzgado, int turno, String caratulado) {
		this.IUE = iUE;
		this.juzgado = juzgado;
		this.turno = turno;
		this.caratulado = caratulado;
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
	@Column(name = "IUE",unique=true)
	public String getIUE() {
		return IUE;
	}
	public void setIUE(String iUE) {
		IUE = iUE;
	}

	@Basic
	public String getJuzgado() {
		return juzgado;
	}
	public void setJuzgado(String juzgado) {
		this.juzgado = juzgado;
	}

	@Basic
	public int getTurno() {
		return turno;
	}
	public void setTurno(int turno) {
		this.turno = turno;
	}

	@Basic
	public String getCaratulado() {
		return caratulado;
	}
	public void setCaratulado(String caratulado) {
		this.caratulado = caratulado;
	}
	
}
