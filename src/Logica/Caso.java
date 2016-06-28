package Logica;

import javax.persistence.*;

@Entity
public class Caso {
	private long id;
	private String IUE;
	private String juzgado;
	private int turno;
	private String caratulado;
	

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	@Basic
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
