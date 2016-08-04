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
			
			fachada.eliminarCaso(actualUsr, "2-1451/2015");
			
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
		fachada.agregarMensaje(actualUsr, "2-1451/2015", "Master", new Date(20,07,2016), "Mensaje de prueba");
		fachada.agregarMensaje(actualUsr, "2-1451/2015", "Master", new Date(20,07,2016), "Mensaje de prueba");
		fachada.agregarMensaje(actualUsr, "2-1451/2015", "Master", new Date(20,07,2016), "Mensaje de prueba");
		
		try {
			assertEquals(true,ControladoraCaso.existeCaso(actualUsr, "2-1231/2015"));
			assertEquals(true,ControladoraCaso.existeCaso(actualUsr, "2-1451/2015"));
			assertEquals(true,ControladoraCaso.existeCaso(actualUsr, "2-5431/2015"));

			assertEquals(false,fachada.obtenerInvolucrados(actualUsr, "2-1451/2015").isEmpty());
			assertEquals(false,fachada.obtenerConversacion(actualUsr, "2-1451/2015").isEmpty());
			
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
	
	@Test
	public void testAgregarCasosCorrectos () {
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
	}
	
	@Test
	public void asociarYDesasociarUsuarioACaso () {
		Fachada fachada = Fachada.getInstancia();

		String actualUsr = fachada.login("Master", "Master1!");
		
		fachada.agregarCaso(actualUsr, "2-1231/2015", "familia", 12, "Juan Pedro");
		fachada.agregarCaso(actualUsr, "2-1451/2015", "familia", 12, "Juan Pedro");
		fachada.agregarCaso(actualUsr, "2-5431/2015", "familia", 12, "Juan Pedro");
		
		assertEquals("completado", fachada.asociarUsuarioACaso(actualUsr, "Master", "2-5431/2015", "profecional"));
		assertNotEquals(null, fachada.obtenerCasosPorUsuario(actualUsr, "Master"));
		assertEquals("completado", fachada.desasociarUsuarioACaso(actualUsr, "Master", "2-5431/2015"));
		assertEquals(null, fachada.obtenerCasosPorUsuario(actualUsr, "Master"));
	}
	
	public void asociarUsuarioACasoTipoIncorrecto () {

		Fachada fachada = Fachada.getInstancia();

		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("tipo", fachada.asociarUsuarioACaso(actualUsr, "Master", "2-1451/2015", null));
		assertEquals("tipo", fachada.asociarUsuarioACaso(actualUsr, "Master", "2-1451/2015", ""));
		assertEquals("tipo", fachada.asociarUsuarioACaso(actualUsr, "Master", "2-1451/2015", " "));
		assertEquals("tipo", fachada.asociarUsuarioACaso(actualUsr, "Master", "2-1451/2015", "something"));
		assertEquals("tipo", fachada.asociarUsuarioACaso(actualUsr, "Master", "2-1451/2015", " profecional "));
	}
	
	@Test
	public void testAgregarCasoIUEIncorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("IUE",fachada.agregarCaso(actualUsr, " ", "familia", 12, "Juan Pedro"));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "textoerror", "familia", 12, "Juan Pedro"));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "1234", "familia", 12, "Juan Pedro"));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "12312-12312", "familia", 12, "Juan Pedro"));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "12312/12312", "familia", 12, "Juan Pedro"));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "2-1234/asda", "familia", 12, "Juan Pedro"));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "q-1231/2015", "familia", 12, "Juan Pedro"));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "2-aaaa/2015", "familia", 12, "Juan Pedro"));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "2-1451/20153", "familia", 12, "Juan Pedro"));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "2-143333351/2015", "familia", 12, "Juan Pedro"));
	}
	
	@Test
	public void testAgregarCasoJuzgadoIncorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("|juzgado",fachada.agregarCaso(actualUsr, "2-1459/2015", " ", 12, "Juan Pedro"));
		assertEquals("|juzgado",fachada.agregarCaso(actualUsr, "2-1459/2015", "2123123", 12, "Juan Pedro"));
		assertEquals("|juzgado",fachada.agregarCaso(actualUsr, "2-1459/2015", "123123error", 12, "Juan Pedro"));
		assertEquals("|juzgado",fachada.agregarCaso(actualUsr, "2-1459/2015", "error123123", 12, "Juan Pedro"));
		assertEquals("|juzgado",fachada.agregarCaso(actualUsr, "2-1459/2015", "error!", 12, "Juan Pedro"));
		assertEquals("|juzgado",fachada.agregarCaso(actualUsr, "2-1459/2015", "!error", 12, "Juan Pedro"));
		assertEquals("|juzgado",fachada.agregarCaso(actualUsr, "2-1459/2015", "error error error", 12, "Juan Pedro"));
	}
	
	@Test
	public void testAgregarCasoTurnoIncorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("|turno",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", -1, "Juan Pedro"));
		assertEquals("|turno",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", 444, "Juan Pedro"));
	}
	
	@Test
	public void testAgregarCasoCaratuladoIncorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("|caratulado",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", 12, " "));
		assertEquals("|caratulado",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", 12, "Juan 123123"));
		assertEquals("|caratulado",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", 12, "123213 !!!"));
		assertEquals("|caratulado",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", 12, "123213"));
		assertEquals("|caratulado",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", 12, "!!!!!!"));
		assertEquals("|caratulado",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", 12, "!!!!!! Pedro"));
		assertEquals("|caratulado",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", 12, "Juan !!!!!!"));
	}
	
	@Test
	public void testAgregarInvolucradoIUEIncorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, " ", new Date(11,12,1992), "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "textoerror", new Date(11,12,1992), "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "1234", new Date(11,12,1992), "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "12312-12312", new Date(11,12,1992), "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "12312/12312", new Date(11,12,1992), "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "2-1234/asda", new Date(11,12,1992), "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "q-1231/2015", new Date(11,12,1992), "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "2-aaaa/2015", new Date(11,12,1992), "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "2-1451/20153", new Date(11,12,1992), "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "2-143333351/2015", new Date(11,12,1992), "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
	}
	
	@Test
	public void testAgregarInvolucradoNombreIncorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), " ", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "12312", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "Raquel 123123", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "123123 123123", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "Raq233uel Pe23rez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "Migu!!el Rodri!!guez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "!!!!!", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "Raquel", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
	}
	
	@Test
	public void testAgregarMensajeCedulaIncorrecta () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "Raquel Perez", "", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "Raquel Perez", " ", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "Raquel Perez", "asdadasd", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "Raquel Perez", "asdasdasd123123", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "Raquel Perez", "17823243a", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "Raquel Perez", "a17823243", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "Raquel Perez", "178 3243", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "Raquel Perez", "17823asd", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "Raquel Perez", "07823243", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "Raquel Perez", "0782324", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", new Date(11,12,1992), "Raquel Perez", "078232433", 
				"uruguaya", "Saint Bois 5063", "hija"));
	}
	
	@Test
	public void testAgregarMensajeIUEIncorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("IUE", fachada.agregarMensaje(actualUsr, " ", "Master", new Date(20,07,2016), "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "textoerror", "Master", new Date(20,07,2016), "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "1234", "Master", new Date(20,07,2016), "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "12312-12312", "Master", new Date(20,07,2016), "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "12312/12312", "Master", new Date(20,07,2016), "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "2-1234/asda", "Master", new Date(20,07,2016), "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "q-1231/2015", "Master", new Date(20,07,2016), "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "2-aaaa/2015", "Master", new Date(20,07,2016), "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "2-1451/20153", "Master", new Date(20,07,2016), "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "2-143333351/2015", "Master", new Date(20,07,2016), "Mensaje de prueba"));
	}
	
	@Test
	public void testAgregarMensajeUsuarioIncorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("|usuario", fachada.agregarMensaje(actualUsr, "2-1459/2015", "", new Date(20,07,2016), "Mensaje de prueba"));
		assertEquals("|usuario", fachada.agregarMensaje(actualUsr, "2-1459/2015", " ", new Date(20,07,2016), "Mensaje de prueba"));
		assertEquals("|usuario", fachada.agregarMensaje(actualUsr, "2-1459/2015", "123123", new Date(20,07,2016), "Mensaje de prueba"));
		assertEquals("|usuario", fachada.agregarMensaje(actualUsr, "2-1459/2015", "abcdabcdabcdabcdabcdabcdabcdabcdabcd", 
				new Date(20,07,2016), "Mensaje de prueba"));
	}
	
	@Test
	public void testAgregarMensajeContenidoIncorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("|contenido", fachada.agregarMensaje(actualUsr, "2-1459/2015", "Master", new Date(20,07,2016), ""));
		assertEquals("|contenido", fachada.agregarMensaje(actualUsr, "2-1459/2015", "Master", new Date(20,07,2016), " "));
		assertEquals("|contenido", fachada.agregarMensaje(actualUsr, "2-1459/2015", "Master", new Date(20,07,2016), null));
	}
}
