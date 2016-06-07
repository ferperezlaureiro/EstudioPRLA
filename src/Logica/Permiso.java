package Logica;

public class Permiso {
	private String code;
	private String nombre;
	
	
	public Permiso(String code, String nombre) {
		super();
		this.code = code;
		this.nombre = nombre;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
