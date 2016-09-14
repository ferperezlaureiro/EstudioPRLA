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
								@QueryParam("caratulado") String caratulado,
								@QueryParam("suscrito") boolean suscrito) {
		try {
			return ControladoraCaso.agregarCaso(usuarioActual, iUE, juzgado, turno, caratulado, suscrito);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
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
			return ControladoraCaso.agregarInvolucrado(usuarioActual, iUE, fechaDeNacimiento, nombre, cedula, nacionalidad, domicilio, clase);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@PUT
    @Path("/modificarCaso")
	@Produces(MediaType.TEXT_HTML)
	public String modificarCaso (@QueryParam("usrKey") String usuarioActual, 
									@QueryParam("iUEUsado") String iUEUsado, 
									@QueryParam("iUE") String iUE, 
									@QueryParam("juzgado") String juzgado, 
									@QueryParam("turno") int turno, 
									@QueryParam("caratulado") String caratulado,
									@QueryParam("suscrito") boolean suscrito) {
		try {
			return ControladoraCaso.modificarCaso(usuarioActual, iUEUsado, iUE, juzgado, turno, caratulado, suscrito);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@POST
    @Path("/asociarUsuarioACaso")
	@Produces(MediaType.TEXT_HTML)
	public String asociarUsuarioACaso (@QueryParam("usrKey") String usuarioActual, 
										@QueryParam("usuario") String usuario, 
										@QueryParam("iUE") String iUE, 
										@QueryParam("tipo") String tipo) {
		try {
			return ControladoraCaso.asociarUsuarioACaso(usuarioActual, usuario, iUE, tipo);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@DELETE
    @Path("/desasociarUsuarioACaso")
	@Produces(MediaType.TEXT_HTML)
	public String desasociarUsuarioACaso (@QueryParam("usrKey") String usuarioActual, 
											@QueryParam("usuario") String usuario, 
											@QueryParam("iUE") String iUE) {
		try {
			return ControladoraCaso.desasociarUsuarioACaso(usuarioActual, usuario, iUE);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
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
			return ControladoraCaso.modificarInvolucrado(usuarioActual, iUE, cedulaUsada, fechaDeNacimiento, nombre, cedula, nacionalidad, domicilio, clase);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@POST
    @Path("/agregarMensaje")
	@Produces(MediaType.TEXT_HTML)
	public String agregarMensaje (@QueryParam("usrKey") String usuarioActual,
									@QueryParam("iUE") String iUE,
									@QueryParam("contenido") String contenido) {
		try {
			return ControladoraCaso.agregarMensaje(usuarioActual, iUE, contenido);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@GET
    @Path("/obtenerCasos")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Caso> obtenerCasos (@QueryParam("usrKey") String usuarioActual) {
		try {
			return ControladoraCaso.obtenerCasos(usuarioActual);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
    @Path("/obtenerCasosPorUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Caso> obtenerCasosPorUsuario (@QueryParam("usrKey") String usuarioActual,
													@QueryParam("usuario") String usuario) {
		try {
			return ControladoraCaso.obtenerCasosPorUsuario(usuarioActual, usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
    @Path("/obtenerCasosNoAsignadosAUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Caso> obtenerCasosNoAsignadosAUsuario (@QueryParam("usrKey") String usuarioActual,
													@QueryParam("usuario") String usuario) {
		try {
			return ControladoraCaso.obtenerCasosNoAsignadosAUsuario(usuarioActual, usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
    @Path("/obtenerUsuariosPorCaso")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Usuario> obtenerUsuariosPorCaso (@QueryParam("usrKey") String usuarioActual,
													@QueryParam("iUE") String iUE) {
		try {
			return ControladoraCaso.obtenerUsuariosPorCaso(usuarioActual, iUE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
    @Path("/obtenerUsuariosNoAsignadosACaso")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Usuario> obtenerUsuariosNoAsignadosACaso (@QueryParam("usrKey") String usuarioActual,
													@QueryParam("iUE") String iUE) {
		try {
			return ControladoraCaso.obtenerUsuariosNoAsignadosACaso(usuarioActual, iUE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
    @Path("/obtenerCasoPorIUE")
	@Produces(MediaType.APPLICATION_JSON)
	public Caso obtenerCasoPorIUE (@QueryParam("usrKey") String usuarioActual,
									@QueryParam("iUE") String iUE) {
		try {
			return ControladoraCaso.obtenerCasoPorIUE(usuarioActual, iUE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
    @Path("/obtenerConversacion")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Mensaje> obtenerConversacion (@QueryParam("usrKey") String usuarioActual,
													@QueryParam("iUE") String iUE) {
		try {
			return ControladoraCaso.obtenerConversacion(usuarioActual, iUE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
    @Path("/obtenerInvolucrados")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Involucrado> obtenerInvolucrados (@QueryParam("usrKey") String usuarioActual,
														@QueryParam("iUE") String iUE) {
		try {
			return ControladoraCaso.obtenerInvolucrados(usuarioActual, iUE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@DELETE
    @Path("/eliminarCaso")
	@Produces(MediaType.TEXT_HTML)
	public String eliminarCaso (@QueryParam("usrKey") String usuarioActual,
								@QueryParam("iUE") String iUE) {
		try {
			return ControladoraCaso.eliminarCaso(usuarioActual, iUE);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@DELETE
    @Path("/eliminarInvolucrado")
	@Produces(MediaType.TEXT_HTML)
	public String eliminarInvolucrado (@QueryParam("usrKey") String usuarioActual,
										@QueryParam("iUE") String iUE,
										@QueryParam("ciInvolucrado") String ciInvolucrado) {
		try {
			return ControladoraCaso.eliminarInvolucrado(usuarioActual, iUE, ciInvolucrado);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@OPTIONS
	@Path("/casos")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations(){
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}
}
