package logica;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "movimiento")
public class Movimiento implements Serializable{
	private static final long serialVersionUID = 1L;
	private int index;
	private String decreto;
	private String fecha;
	private String tipo;
	private String vencimiento;
	private String sede;
	private String IUE;
	
	protected Movimiento(){}

	public Movimiento(String decreto, String fecha, String tipo, String vencimiento, String sede, String IUE) {
		this.decreto = decreto;
		this.fecha = fecha;
		this.tipo = tipo;
		this.vencimiento = vencimiento;
		this.sede = sede;
		this.IUE = IUE;
	}

	public String getDecreto() {
		return decreto;
	}
	@XmlElement
	public void setDecreto(String decreto) {
		this.decreto = decreto;
	}

	public String getFecha() {
		return fecha;
	}
	@XmlElement
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}
	@XmlElement
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getVencimiento() {
		return vencimiento;
	}
	@XmlElement
	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	public String getSede() {
		return sede;
	}
	@XmlElement
	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getIUE() {
		return IUE;
	}
	@XmlElement
	public void setIUE(String IUE) {
		this.IUE = IUE;
	}

	public int getIndex() {
		return index;
	}
	@XmlElement
	public void setIndex(int index) {
		this.index = index;
	}
}
