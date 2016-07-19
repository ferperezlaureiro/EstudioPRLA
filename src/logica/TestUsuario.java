package logica;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

public class TestUsuario {

	
	@Test
	public void testEliminarUsuario() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");
		
		fachada.AgregarUsuario(actualUsr, "Mirta", "Adasd1!", "Graciela Laureiro", "17823243", "Gracielalaureiro@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		fachada.AgregarUsuario(actualUsr, "Fernando", "Adasd1!", "Fernando Perez", "17823243", "Fernandopr_57@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		fachada.AgregarUsuario(actualUsr, "Ernesto", "Adasd1!", "Ernesto Rodriguez", "17823243", "Ernestorod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		
		assertEquals(true,fachada.existeUsuario("Graciela"));
		assertEquals(true,fachada.existeUsuario("Fernando"));
		assertEquals(true,fachada.existeUsuario("Ernesto"));
		
		fachada.eliminarUsuario(actualUsr, "Graciela");
		
		assertEquals(false,fachada.existeUsuario("Graciela"));
	}

	@Test
	public void testAgregarDiferentesUsuariosCorrectos() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");
		
		fachada.AgregarUsuario(actualUsr, "Graciela", "Adasd1!", "Graciela Laureiro", "17823243", "Gracielalaureiro@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(true,fachada.existeUsuario("Graciela"));
		
		fachada.AgregarUsuario(actualUsr, "Fernando", "Adasd1!", "Fernando Perez", "17823243", "Fernandopr_57@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(true,fachada.existeUsuario("Fernando"));
		
		fachada.AgregarUsuario(actualUsr, "Ernesto", "Adasd1!", "Ernesto Rodriguez", "17823243", "Ernestorod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(true,fachada.existeUsuario("Ernesto"));
	}

	@Test
	public void testAgregarUsuarioIncorrecto() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");
		
		String retorno = fachada.AgregarUsuario(actualUsr, "", "Adasd1!", "Graciela Laureiro", "17823243", "Gracielalaureiro@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"usuario");
		
		retorno = fachada.AgregarUsuario(actualUsr, "123123", "Adasd1!", "Fernando Perez", "17823243", "Fernandopr_57@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"usuario");
		
		retorno = fachada.AgregarUsuario(actualUsr, " ", "Adasd1!", "Ernesto Rodriguez", "17823243", "Ernestorod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"usuario");
	}

	@Test
	public void testAgregarContraseniaIncorrecta() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");
		
		String retorno = fachada.AgregarUsuario(actualUsr, "Miguel", " ", "Miguel Laureiro", "17823243", "Miguellaureiro@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|contrasenia");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "", "Miguel Perez", "17823243", "Miguelpr_57@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|contrasenia");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "1231231", "Miguel Rodriguez", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|contrasenia");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "asdasdasd", "Miguel Rodriguez", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|contrasenia");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "ASDASDASD", "Miguel Rodriguez", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|contrasenia");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "!!!!!!", "Miguel Rodriguez", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|contrasenia");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "asdasd212312", "Miguel Rodriguez", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|contrasenia");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "ASDASD123123", "Miguel Rodriguez", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|contrasenia");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "ASDDASDasdasdd!!!", "Miguel Rodriguez", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|contrasenia");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "As1!", "Miguel Rodriguez", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|contrasenia");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aas das1!", "Miguel Rodriguez", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|contrasenia");
	}

	@Test
	public void testAgregarNombreIncorrecto() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");
		
		String retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "", "17823243", "Miguellaureiro@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|nombre");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", " ", "17823243", "Miguelpr_57@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|nombre");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "12312", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|nombre");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel 123123", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|nombre");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "123123 123123", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|nombre");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Migu12el Rodr12iguez", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|nombre");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Migu!!el Rodri!!guez", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|nombre");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "!!!!!", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|nombre");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|nombre");
	}

	@Test
	public void testAgregarCedulaIncorrecta() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");
		
		String retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "", "Miguellaureiro@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cedula");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", " ", "Miguelpr_57@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cedula");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "asdadasd", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cedula");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "asdasdasd123123", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cedula");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243a", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cedula");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "a17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cedula");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "178 3243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cedula");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823asd", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cedula");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "07823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cedula");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "0782324", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cedula");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "078232433", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cedula");
	}

	@Test
	public void testAgregarEmailIncorrecta() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");
		
		String retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|email");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", " ", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|email");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "asdasdasdasd", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|email");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "3123123123", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|email");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|email");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|email");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|email");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrodgmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|email");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gma il.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|email");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@2312.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|email");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.12313", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|email");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "!!!!!!!!@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|email");
	}

	@Test
	public void testAgregarTelIncorrecto() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");
		
		String retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|tel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", " ", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|tel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "asdasdasd", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|tel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "1234asdasd", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|tel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "asdasd1231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|tel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "2341!!!!", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|tel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "!!!412311", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|tel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "!!!!!!!", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|tel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "1234 231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|tel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "2341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|tel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "112341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|tel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "02341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|tel");
	}

	@Test
	public void testAgregarCelIncorrecto() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");
		
		String retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				" ", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"dasdasdasd", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"0999dasdsd", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"asdd54750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"0999!!!!", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"!!!!!4750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"!!!!!!!!", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099 4750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"09995475", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"0999547501", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cel");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"129954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|cel");
	}
	
	@Test
	public void testAgregarDomicilioIncorrecto() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");
		
		String retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", " ", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilio");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilio");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilio");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "!!!!!!", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilio");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Sa!!int Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilio");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 115063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilio");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063!!", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilio");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "1111 Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilio");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "1111 saint bois", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilio");
	}
	
	@Test
	public void testAgregarDomicilioLaboralIncorrecto() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");
		
		String retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", " ", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilioLaboral");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilioLaboral");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilioLaboral");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "!!!!!!", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilioLaboral");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Tre!!inta y tres 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilioLaboral");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 981334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilioLaboral");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334!!", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilioLaboral");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "1111 Treinta 1334", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilioLaboral");
		
		retorno = fachada.AgregarUsuario(actualUsr, "Miguel", "Aasdsa1!", "Miguel Laureiro", "17823243", "Miguelrod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "1111 treinta y tres", "34234", new Date(11,12,1992));
		assertEquals(retorno,"|domicilioLaboral");
	}
	
	@Test
	public void testUsuarioYContraseniaCorrectos() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");
		
		fachada.AgregarUsuario(actualUsr, "Graciela", "Adasd1!", "Graciela Laureiro", "17823243", "Gracielalaureiro@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		fachada.AgregarUsuario(actualUsr, "Fernando", "Adasd1!", "Fernando Perez", "17823243", "Fernandopr_57@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		fachada.AgregarUsuario(actualUsr, "Ernesto", "Adasd1!", "Ernesto Rodriguez", "17823243", "Ernestorod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));

		Usuario u = fachada.buscarUsuario("Graciela");
		String usrEncriptado = Utilidades.Encriptar(u.getUsuario() + "-" + u.getCedula() + "-" + u.getContrasenia());
		assertEquals(usrEncriptado, fachada.login("Graciela", "Adasd1!"));
		
		u = fachada.buscarUsuario("Fernando");
		usrEncriptado = Utilidades.Encriptar(u.getUsuario() + "-" + u.getCedula() + "-" + u.getContrasenia());
		assertEquals(usrEncriptado, fachada.login("Fernando", "Adasd1!"));
		
		u = fachada.buscarUsuario("Ernesto");
		usrEncriptado = Utilidades.Encriptar(u.getUsuario() + "-" + u.getCedula() + "-" + u.getContrasenia());
		assertEquals(usrEncriptado, fachada.login("Ernesto", "Adasd1!"));
	}
	
	@Test
	public void testUsuarioYContraseniaIncorrectos() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");
		
		fachada.AgregarUsuario(actualUsr, "Graciela", "Adasd1!", "Graciela Laureiro", "17823243", "Gracielalaureiro@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		fachada.AgregarUsuario(actualUsr, "Fernando", "Adasd1!", "Fernando Perez", "17823243", "Fernandopr_57@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		fachada.AgregarUsuario(actualUsr, "Ernesto", "Adasd1!", "Ernesto Rodriguez", "17823243", "Ernestorod@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));

		assertEquals("contrasenia", fachada.login("Graciela", "1111"));
		assertEquals("contrasenia", fachada.login("Fernando", "1111"));
		assertEquals("contrasenia", fachada.login("Ernesto", "1111"));

		assertEquals("usuario", fachada.login("no user", "1111"));
		assertEquals("usuario", fachada.login("no user one", "1111"));
		assertEquals("usuario", fachada.login("no user two", "1111"));
	}
	
	@Test
	public void testBuscarUsuario() {
		Fachada fachada = Fachada.getInstancia();
		
		String actualUsr = fachada.login("Master", "Master1!");
		
		fachada.AgregarUsuario(actualUsr, "Graciela", "Adasd1!", "Graciela Laureiro", "17823243", "Gracielalaureiro@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));
		
		Usuario u = new Usuario("Graciela", "Adasd1!", "Graciela Laureiro", "17823243", "Gracielalaureiro@gmail.com", "12341231", 
				"099954750", "Saint Bois 5063", "Treinta y tres 1334", "34234", new Date(11,12,1992));

		assertEquals(u.getUsuario(), fachada.buscarUsuario("Graciela").getUsuario());
		assertEquals(null, fachada.buscarUsuario("Gabriela"));
	}
}
