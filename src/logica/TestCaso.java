package logica;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

public class TestCaso {

	@Test
	public void testEliminarCasoSinInvolucradosNiMensajes() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		fachada.agregarCaso(actualUsr, "2-1231/2015", "familia", 12, "Juan Pedro");
		fachada.agregarCaso(actualUsr, "2-1451/2015", "familia", 12, "Juan Pedro");
		fachada.agregarCaso(actualUsr, "2-5431/2015", "familia", 12, "Juan Pedro");
		
		try {
			assertEquals(true,ControladoraCaso.existeCaso(actualUsr, "2-1231/2015"));
			assertEquals(true,ControladoraCaso.existeCaso(actualUsr, "2-1451/2015"));
			assertEquals(true,ControladoraCaso.existeCaso(actualUsr, "2-5431/2015"));	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		fachada.eliminarCaso(actualUsr, "2-1451/2015");
		
		try {
			assertEquals(false,ControladoraCaso.existeCaso(actualUsr, "2-1451/2015"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testEliminarCasoConInvolucradosYMensajes() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		fachada.agregarCaso(actualUsr, "2-1231/2015", "familia", 12, "Juan Pedro");
		fachada.agregarCaso(actualUsr, "2-1451/2015", "familia", 12, "Juan Pedro");
		fachada.agregarCaso(actualUsr, "2-5431/2015", "familia", 12, "Juan Pedro");
		fachada.agregarInvolucrado(actualUsr, "2-1451/2015", new Date(11,12,1992), "Hernesto Perez", "49290325", "uruguayo", "Saint Bois 5063", 
				"hijo");
		fachada.agregarInvolucrado(actualUsr, "2-1451/2015", new Date(11,12,1992), "Raquel Perez", "49290325", "uruguaya", "Saint Bois 5063", 
				"hija");
		fachada.agregarInvolucrado(actualUsr, "2-1451/2015", new Date(11,12,1992), "Maria Perez", "49290325", "uruguaya", "Saint Bois 5063", 
				"hija");
		fachada.agregarMensaje(actualUsr, "2-1451/2015", "Fernando", new Date(20,07,2016), "Mensaje de prueba");
		fachada.agregarMensaje(actualUsr, "2-1451/2015", "Fernando", new Date(20,07,2016), "Mensaje de prueba");
		fachada.agregarMensaje(actualUsr, "2-1451/2015", "Fernando", new Date(20,07,2016), "Mensaje de prueba");
		
		try {
			assertEquals(true,ControladoraCaso.existeCaso(actualUsr, "2-1231/2015"));
			assertEquals(true,ControladoraCaso.existeCaso(actualUsr, "2-1451/2015"));
			assertEquals(true,ControladoraCaso.existeCaso(actualUsr, "2-5431/2015"));

			assertEquals(false,fachada.obtenerConversacion(actualUsr, "2-1451/2015").isEmpty());
			assertEquals(false,fachada.obtenerInvolucrados(actualUsr, "2-1451/2015").isEmpty());
			
			fachada.eliminarCaso(actualUsr, "2-1451/2015");
			
			assertEquals(false,ControladoraCaso.existeCaso(actualUsr, "2-1451/2015"));
			
			assertEquals(true,fachada.obtenerInvolucrados(actualUsr, "2-1451/2015").isEmpty());
			assertEquals(true,fachada.obtenerConversacion(actualUsr, "2-1451/2015").isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEliminarInvolucrado() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		fachada.agregarCaso(actualUsr, "2-1451/2015", "familia", 12, "Juan Pedro");

		fachada.agregarInvolucrado(actualUsr, "2-1451/2015", new Date(11,12,1992), "Hernesto Perez", "49290325", "uruguayo", "Saint Bois 5063", 
				"hijo");
		fachada.agregarInvolucrado(actualUsr, "2-1451/2015", new Date(11,12,1992), "Raquel Perez", "49290325", "uruguaya", "Saint Bois 5063", 
				"hija");
		fachada.agregarInvolucrado(actualUsr, "2-1451/2015", new Date(11,12,1992), "Maria Perez", "49290325", "uruguaya", "Saint Bois 5063", 
				"hija");
		
		try {
			assertEquals(true, ControladoraCaso.existeCaso(actualUsr, "2-1451/2015"));

			assertEquals(true, ControladoraCaso.existeInvolucrado(actualUsr, "2-1451/2015", "49290325"));
			
			fachada.eliminarInvolucrado(actualUsr, "2-1451/2015", "49290325");

			assertEquals(false, ControladoraCaso.existeInvolucrado(actualUsr, "2-1451/2015", "49290325"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
