
package logica;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCaso {

	@Test
	public void testEliminarCasoSinInvolucradosNiMensajes() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		fachada.agregarCaso(actualUsr, "2-1231/2015", "familia", 12, "Juan Pedro", false);
		fachada.agregarCaso(actualUsr, "2-1451/2015", "familia", 12, "Juan Pedro", false);
		fachada.agregarCaso(actualUsr, "2-5431/2015", "familia", 12, "Juan Pedro", false);
		
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

		fachada.agregarCaso(actualUsr, "2-1231/2015", "familia", 12, "Juan Pedro", false);
		fachada.agregarCaso(actualUsr, "2-1451/2015", "familia", 12, "Juan Pedro", false);
		fachada.agregarCaso(actualUsr, "2-5431/2015", "familia", 12, "Juan Pedro", false);
		fachada.agregarInvolucrado(actualUsr, "2-1451/2015", "11/12/1992", "Hernesto Perez", "49290325", "uruguayo", "Saint Bois 5063", 
				"hijo");
		fachada.agregarInvolucrado(actualUsr, "2-1451/2015", "11/12/1992", "Raquel Perez", "49290325", "uruguaya", "Saint Bois 5063", 
				"hija");
		fachada.agregarInvolucrado(actualUsr, "2-1451/2015", "11/12/1992", "Maria Perez", "49290325", "uruguaya", "Saint Bois 5063", 
				"hija");
		fachada.agregarMensaje(actualUsr, "2-1451/2015", "Mensaje de prueba");
		fachada.agregarMensaje(actualUsr, "2-1451/2015", "Mensaje de prueba");
		fachada.agregarMensaje(actualUsr, "2-1451/2015", "Mensaje de prueba");
		
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

		fachada.agregarCaso(actualUsr, "2-1451/2015", "familia", 12, "Juan Pedro", false);

		fachada.agregarInvolucrado(actualUsr, "2-1451/2015", "11/12/1992", "Hernesto Perez", "49290325", "uruguayo", "Saint Bois 5063", 
				"hijo");
		fachada.agregarInvolucrado(actualUsr, "2-1451/2015", "11/12/1992", "Raquel Perez", "49290325", "uruguaya", "Saint Bois 5063", 
				"hija");
		fachada.agregarInvolucrado(actualUsr, "2-1451/2015", "11/12/1992", "Maria Perez", "49290325", "uruguaya", "Saint Bois 5063", 
				"hija");
		
		try {
			assertEquals(true, ControladoraCaso.existeCaso(actualUsr, "2-1451/2015"));

			assertNotEquals(null, ControladoraCaso.obtenerInvolucrado(actualUsr, "2-1451/2015", "49290325"));
			
			fachada.eliminarInvolucrado(actualUsr, "2-1451/2015", "49290325");

			assertEquals(null, ControladoraCaso.obtenerInvolucrado(actualUsr, "2-1451/2015", "49290325"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAgregarCasosCorrectos () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");
		
		fachada.agregarCaso(actualUsr, "2-1231/2015", "familia", 12, "Juan Pedro", false);
		fachada.agregarCaso(actualUsr, "2-1451/2015", "familia", 12, "Juan Pedro", false);
		fachada.agregarCaso(actualUsr, "2-5431/2015", "familia", 12, "Juan Pedro", false);
		
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
		
		fachada.agregarCaso(actualUsr, "2-1231/2015", "familia", 12, "Juan Pedro", false);
		fachada.agregarCaso(actualUsr, "2-1451/2015", "familia", 12, "Juan Pedro", false);
		fachada.agregarCaso(actualUsr, "2-5431/2015", "familia", 12, "Juan Pedro", false);
		
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

		assertEquals("IUE",fachada.agregarCaso(actualUsr, " ", "familia", 12, "Juan Pedro", false));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "textoerror", "familia", 12, "Juan Pedro", false));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "1234", "familia", 12, "Juan Pedro", false));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "12312-12312", "familia", 12, "Juan Pedro", false));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "12312/12312", "familia", 12, "Juan Pedro", false));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "2-1234/asda", "familia", 12, "Juan Pedro", false));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "q-1231/2015", "familia", 12, "Juan Pedro", false));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "2-aaaa/2015", "familia", 12, "Juan Pedro", false));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "2-1451/20153", "familia", 12, "Juan Pedro", false));
		assertEquals("IUE",fachada.agregarCaso(actualUsr, "2-143333351/2015", "familia", 12, "Juan Pedro", false));
	}
	
	@Test
	public void testAgregarCasoJuzgadoIncorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("|juzgado",fachada.agregarCaso(actualUsr, "2-1459/2015", " ", 12, "Juan Pedro", false));
		assertEquals("|juzgado",fachada.agregarCaso(actualUsr, "2-1459/2015", "2123123", 12, "Juan Pedro", false));
		assertEquals("|juzgado",fachada.agregarCaso(actualUsr, "2-1459/2015", "123123error", 12, "Juan Pedro", false));
		assertEquals("|juzgado",fachada.agregarCaso(actualUsr, "2-1459/2015", "error123123", 12, "Juan Pedro", false));
		assertEquals("|juzgado",fachada.agregarCaso(actualUsr, "2-1459/2015", "error!", 12, "Juan Pedro", false));
		assertEquals("|juzgado",fachada.agregarCaso(actualUsr, "2-1459/2015", "!error", 12, "Juan Pedro", false));
		assertEquals("|juzgado",fachada.agregarCaso(actualUsr, "2-1459/2015", "error error error", 12, "Juan Pedro", false));
	}
	
	@Test
	public void testAgregarCasoTurnoIncorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("|turno",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", -1, "Juan Pedro", false));
		assertEquals("|turno",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", 444, "Juan Pedro", false));
	}
	
	@Test
	public void testAgregarCasoCaratuladoIncorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("|caratulado",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", 12, " ", false));
		assertEquals("|caratulado",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", 12, "Juan 123123", false));
		assertEquals("|caratulado",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", 12, "123213 !!!", false));
		assertEquals("|caratulado",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", 12, "123213", false));
		assertEquals("|caratulado",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", 12, "!!!!!!", false));
		assertEquals("|caratulado",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", 12, "!!!!!! Pedro", false));
		assertEquals("|caratulado",fachada.agregarCaso(actualUsr, "2-1459/2015", "familia", 12, "Juan !!!!!!", false));
	}
	
	@Test
	public void testAgregarInvolucradoIUEIncorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, " ", "11/12/1992", "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "textoerror", "11/12/1992", "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "1234", "11/12/1992", "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "12312-12312", "11/12/1992", "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "12312/12312", "11/12/1992", "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "2-1234/asda", "11/12/1992", "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "q-1231/2015", "11/12/1992", "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "2-aaaa/2015", "11/12/1992", "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "2-1451/20153", "11/12/1992", "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("IUE", fachada.agregarInvolucrado(actualUsr, "2-143333351/2015", "11/12/1992", "Raquel Perez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
	}
	
	@Test
	public void testAgregarInvolucradoNombreIncorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", " ", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "12312", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "Raquel 123123", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "123123 123123", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "Raq233uel Pe23rez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "Migu!!el Rodri!!guez", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "!!!!!", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|nombre", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "Raquel", "49290325", 
				"uruguaya", "Saint Bois 5063", "hija"));
	}
	
	@Test
	public void testAgregarMensajeCedulaIncorrecta () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "Raquel Perez", "", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "Raquel Perez", " ", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "Raquel Perez", "asdadasd", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "Raquel Perez", "asdasdasd123123", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "Raquel Perez", "17823243a", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "Raquel Perez", "a17823243", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "Raquel Perez", "178 3243", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "Raquel Perez", "17823asd", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "Raquel Perez", "07823243", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "Raquel Perez", "0782324", 
				"uruguaya", "Saint Bois 5063", "hija"));
		assertEquals("|cedula", fachada.agregarInvolucrado(actualUsr, "2-1459/2015", "11/12/1992", "Raquel Perez", "078232433", 
				"uruguaya", "Saint Bois 5063", "hija"));
	}
	
	@Test
	public void testAgregarMensajeIUEIncorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("IUE", fachada.agregarMensaje(actualUsr, " ", "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "textoerror", "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "1234", "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "12312-12312", "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "12312/12312", "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "2-1234/asda", "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "q-1231/2015", "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "2-aaaa/2015", "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "2-1451/20153", "Mensaje de prueba"));
		assertEquals("IUE", fachada.agregarMensaje(actualUsr, "2-143333351/2015", "Mensaje de prueba"));
	}
	
	@Test
	public void testAgregarMensajeContenidoIncorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		assertEquals("|contenido", fachada.agregarMensaje(actualUsr, "2-1459/2015", ""));
		assertEquals("|contenido", fachada.agregarMensaje(actualUsr, "2-1459/2015", " "));
		assertEquals("|contenido", fachada.agregarMensaje(actualUsr, "2-1459/2015", null));
	}
	
	@Test
	public void testModificarCasoSinModificarIUE () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		fachada.agregarCaso(actualUsr, "2-5431/2015", "familia", 12, "Juan Pedro", false);

		fachada.modificarCaso(actualUsr, "2-5431/2015", "2-5431/2015", "civil", 11, "Andres Pedro", false);
		try {
			Caso c = ControladoraCaso.obtenerCasoPorIUE(actualUsr, "2-5431/2015");
			
			assertEquals("civil", c.getJuzgado());
			assertEquals(11, c.getTurno());
			assertEquals("Andres Pedro", c.getCaratulado());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModificarCasoConIUECorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		fachada.agregarCaso(actualUsr, "2-5431/2015", "familia", 12, "Juan Pedro", false);

		fachada.modificarCaso(actualUsr, "2-5431/2015", "2-5031/2015", "civil", 11, "Andres Pedro", false);
		try {
			assertNotEquals(null, ControladoraCaso.obtenerCasoPorIUE(actualUsr, "2-5031/2015"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModificarCasoConIUEIncorrecto () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		fachada.agregarCaso(actualUsr, "2-5431/2015", "familia", 12, "Juan Pedro", false);

		assertEquals("Duplicado", fachada.modificarCaso(actualUsr, "2-5431/2015", "2-1231/2015", "civil", 11, "Andres Pedro", false));
	}
	
	@Test
	public void testModificarInvolucradoSinModificarCedula () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		fachada.agregarInvolucrado(actualUsr, "2-5431/2015", "11/12/1992", "Raquel Perez", "47591234", "uruguaya", "Saint Bois 5063", 
				"hija");

		fachada.modificarInvolucrado(actualUsr, "2-5431/2015", "47591234", "11/12/1992", "Raul Perez", "47591234", "uruguayo", 
				"Chapicuy 5063", "hijo");
		try {
			Involucrado i = ControladoraCaso.obtenerInvolucrado(actualUsr, "2-5431/2015", "47591234");
			
			assertEquals("Raul Perez", i.getNombre());
			assertEquals("uruguayo", i.getNacionalidad());
			assertEquals("Chapicuy 5063", i.getDireccion());
			assertEquals("hijo", i.getClase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModificarInvolucradoConCedulaCorrecta () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		fachada.agregarInvolucrado(actualUsr, "2-5431/2015", "11/12/1992", "Raquel Perez", "47591234", "uruguaya", "Saint Bois 5063", 
				"hija");

		fachada.modificarInvolucrado(actualUsr, "2-5431/2015", "47591234", "11/12/1992", "Raul Perez", "14725836", "argentino", 
				"Guerra 5063", "hermano");
		try {
			assertNotEquals(null, ControladoraCaso.obtenerInvolucrado(actualUsr, "2-5431/2015", "14725836"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModificarInvolucradoConCedulaIncorrecta () {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");

		fachada.agregarInvolucrado(actualUsr, "2-5431/2015", "11/12/1992", "Raquel Perez", "47591234", "uruguaya", "Saint Bois 5063", 
				"hija");

		fachada.agregarInvolucrado(actualUsr, "2-5431/2015", "11/12/1992", "Gerardo Perez", "78945612", "uruguaya", "Saint Bois 5063", 
				"hija");

		assertEquals("Duplicado", fachada.modificarInvolucrado(actualUsr, "2-5431/2015", "47591234", "11/12/1992", "Raul Perez", 
				"78945612", "argentino", "Guerra 5063", "hermano"));
	}
}