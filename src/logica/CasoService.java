package logica;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/CasoService")
public class CasoService {
	
	
	@POST
    @Path("/agregarCaso")
	@Produces(MediaType.TEXT_HTML)
	public String agregarCaso (@QueryParam("usrKey") String usuarioActual, 
								@QueryParam("iUE") String iUE, 
								@QueryParam("juzgado") String juzgado, 
								@QueryParam("turno") int turno, 
								@QueryParam("caratulado") String caratulado) {
		try {
			if (ControladoraCaso.existeCaso(usuarioActual, iUE))
				return "duplicado";
			ControladoraCaso.agregarCaso(usuarioActual, iUE, juzgado, turno, caratulado);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}

	@POST
    @Path("/agregarInvolucrado")
	@Produces(MediaType.TEXT_HTML)
	public String agregarInvolucrado (@QueryParam("usrKey") String usuarioActual,
										@QueryParam("iUE") String iUE,
										@QueryParam("fechaDeNacimiento") String fechaDeNacimiento,
										@QueryParam("nombre") String nombre,
										@QueryParam("cedula") String cedula, 
										@QueryParam("nacionalidad") String nacionalidad,
										@QueryParam("domicilio") String domicilio,
										@QueryParam("clase") String clase) {
		try {
			if (ControladoraCaso.obtenerInvolucrado(usuarioActual, iUE, cedula) != null)
				return "duplicado";
			
			ControladoraCaso.agregarInvolucrado(usuarioActual, iUE, fechaDeNacimiento, nombre, cedula, nacionalidad, domicilio, clase);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}
	
	@PUT
    @Path("/modificarCaso")
	@Produces(MediaType.TEXT_HTML)
	public String modificarCaso (@QueryParam("usrKey") String usuarioActual, 
									@QueryParam("iUEUsado") String iUEUsado, 
									@QueryParam("iUE") String iUE, 
									@QueryParam("juzgado") String juzgado, 
									@QueryParam("turno") int turno, 
									@QueryParam("caratulado") String caratulado) {
		try {
			ControladoraCaso.modificarCaso(usuarioActual, iUEUsado, iUE, juzgado, turno, caratulado);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}
	
	@POST
    @Path("/asociarUsuarioACaso")
	@Produces(MediaType.TEXT_HTML)
	public String asociarUsuarioACaso (@QueryParam("usrKey") String usuarioActual, 
										@QueryParam("usuario") String usuario, 
										@QueryParam("iUE") String iUE, 
										@QueryParam("tipo") String tipo) {
		try {
			ControladoraCaso.asociarUsuarioACaso(usuarioActual, usuario, iUE, tipo);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}

	@DELETE
    @Path("/desasociarUsuarioACaso")
	@Produces(MediaType.TEXT_HTML)
	public String desasociarUsuarioACaso (@QueryParam("usrKey") String usuarioActual, 
											@QueryParam("usuario") String usuario, 
											@QueryParam("iUE") String iUE) {
		try {
			ControladoraCaso.desasociarUsuarioACaso(usuarioActual, usuario, iUE);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}

	@PUT
    @Path("/modificarInvolucrado")
	@Produces(MediaType.TEXT_HTML)
	public String modificarInvolucrado (@QueryParam("usrKey") String usuarioActual,
										@QueryParam("iUE") String iUE,
										@QueryParam("cedulaUsada") String cedulaUsada,
										@QueryParam("fechaDeNacimiento") String fechaDeNacimiento,
										@QueryParam("nombre") String nombre,
										@QueryParam("cedula") String cedula, 
										@QueryParam("nacionalidad") String nacionalidad,
										@QueryParam("domicilio") String domicilio,
										@QueryParam("clase") String clase) {
		try {
			ControladoraCaso.modificarInvolucrado(usuarioActual, iUE, cedulaUsada, fechaDeNacimiento, nombre, cedula, nacionalidad, domicilio, clase);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}
	
	@POST
    @Path("/agregarMensaje")
	@Produces(MediaType.TEXT_HTML)
	public String agregarMensaje (@QueryParam("usrKey") String usuarioActual,
									@QueryParam("iUE") String iUE,
									@QueryParam("contenido") String contenido) {
		try {
			ControladoraCaso.agregarMensaje(usuarioActual, iUE, contenido);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}

	@GET
    @Path("/obtenerCasos")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Caso> obtenerCasos (@QueryParam("usrKey") String usuarioActual) {
		ArrayList<Caso> casos = null;
		try {
			casos = ControladoraCaso.obtenerCasos(usuarioActual);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return casos;
	}

	@GET
    @Path("/obtenerCasosPorUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Caso> obtenerCasosPorUsuario (@QueryParam("usrKey") String usuarioActual,
													@QueryParam("usuario") String usuario) {
		ArrayList<Caso> casos = null;
		try {
			casos = ControladoraCaso.obtenerCasosPorUsuario(usuarioActual, usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return casos;
	}

	@GET
    @Path("/obtenerCasosNoAsignadosAUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Caso> obtenerCasosNoAsignadosAUsuario (@QueryParam("usrKey") String usuarioActual,
													@QueryParam("usuario") String usuario) {
		ArrayList<Caso> casos = null;
		try {
			casos = ControladoraCaso.obtenerCasosNoAsignadosAUsuario(usuarioActual, usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return casos;
	}

	@GET
    @Path("/obtenerUsuariosPorCaso")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Usuario> obtenerUsuariosPorCaso (@QueryParam("usrKey") String usuarioActual,
													@QueryParam("iUE") String iUE) {
		ArrayList<Usuario> usuarios = null;
		try {
			usuarios = ControladoraCaso.obtenerUsuariosPorCaso(usuarioActual, iUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	@GET
    @Path("/obtenerUsuariosNoAsignadosACaso")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Usuario> obtenerUsuariosNoAsignadosACaso (@QueryParam("usrKey") String usuarioActual,
													@QueryParam("iUE") String iUE) {
		ArrayList<Usuario> usuarios = null;
		try {
			usuarios = ControladoraCaso.obtenerUsuariosNoAsignadosACaso(usuarioActual, iUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	@GET
    @Path("/obtenerCasoPorIUE")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("finally")
	public Caso obtenerCasoPorIUE (@QueryParam("usrKey") String usuarioActual,
									@QueryParam("iUE") String iUE) {
		Caso c = null;
		try {
			c = ControladoraCaso.obtenerCasoPorIUE(usuarioActual, iUE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return c;
		}
	}

	@GET
    @Path("/obtenerConversacion")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("finally")
	public ArrayList<Mensaje> obtenerConversacion (@QueryParam("usrKey") String usuarioActual,
													@QueryParam("iUE") String iUE) {
		ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
		try {
			mensajes = ControladoraCaso.obtenerConversacion(usuarioActual, iUE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return mensajes;
		}
	}

	@GET
    @Path("/obtenerInvolucrados")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("finally")
	public ArrayList<Involucrado> obtenerInvolucrados (@QueryParam("usrKey") String usuarioActual,
														@QueryParam("iUE") String iUE) {
		ArrayList<Involucrado> involucrados = new ArrayList<Involucrado>();
		try {
			involucrados = ControladoraCaso.obtenerInvolucrados(usuarioActual, iUE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return involucrados;
		}
	}

	@DELETE
    @Path("/eliminarCaso")
	@Produces(MediaType.TEXT_HTML)
	public String eliminarCaso (@QueryParam("usrKey") String usuarioActual,
								@QueryParam("iUE") String iUE) {
		try {
			ControladoraCaso.eliminarCaso(usuarioActual, iUE);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}

	@DELETE
    @Path("/eliminarInvolucrado")
	@Produces(MediaType.TEXT_HTML)
	public String eliminarInvolucrado (@QueryParam("usrKey") String usuarioActual,
										@QueryParam("iUE") String iUE,
										@QueryParam("ciInvolucrado") String ciInvolucrado) {
		try {
			ControladoraCaso.eliminarInvolucrado(usuarioActual, iUE, ciInvolucrado);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}
	
	@OPTIONS
	@Path("/casos")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations(){
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}
}
