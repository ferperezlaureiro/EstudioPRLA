package logica;

import java.sql.Date;
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

@Path("/UsuarioService")
public class UsuarioService {
	
	Fachada fachada = Fachada.getInstancia();
		
	@GET
    @Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@QueryParam("usuario") String usuario, 
						@QueryParam("contrasenia") String contrasenia) {
		try {
			return ControladoraUsuario.login(usuario, contrasenia);
		} catch (Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@GET
    @Path("/obtenerUsuarios")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("finally")
	public ArrayList<Usuario> obtenerUsuarios(@QueryParam("usrKey") String usrKey) {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			usuarios = ControladoraUsuario.obtenerUsuarios(usrKey);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return usuarios;
		}
	}

	@GET
    @Path("/obtenerUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario obtenerUsuario(@QueryParam("usrKey") String usrKey, 
								  @QueryParam("usuario") String usuario) {
		try {
			return ControladoraUsuario.buscarUsuario(usrKey, usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@POST
	@Path("/agregarUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public String AgregarUsuario(@QueryParam("usrKey") String usrKey, 
									@QueryParam("usuario") String usuario, 
									@QueryParam("contrasenia") String contrasenia, 
									@QueryParam("nombre") String nombre, 
									@QueryParam("cedula") String cedula, 
									@QueryParam("email") String email, 
									@QueryParam("tel") String tel, 
									@QueryParam("cel") String cel, 
									@QueryParam("domicilio") String domicilio, 
									@QueryParam("domicilioLaboral") String domicilioLaboral, 
									@QueryParam("rut") String rut, 
									@QueryParam("fechaDeNacimiento") Date fechaDeNacimiento) {
		try {
			if(ControladoraUsuario.existeUsuario(usuario))
				return "duplicado";
			ControladoraUsuario.AgregarUsuario(usrKey, usuario, contrasenia, nombre, cedula, email, tel, cel, domicilio, domicilioLaboral, rut, fechaDeNacimiento);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}
	
	@PUT
	@Path("/modificarUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public String ModificarUsuario(@QueryParam("usrKey") String usrKey, 
									@QueryParam("usuarioUsado") String usuarioUsado, 
									@QueryParam("usuario") String usuario, 
									@QueryParam("contrasenia") String contrasenia, 
									@QueryParam("nombre") String nombre, 
									@QueryParam("cedula") String cedula, 
									@QueryParam("email") String email, 
									@QueryParam("tel") String tel, 
									@QueryParam("cel") String cel, 
									@QueryParam("domicilio") String domicilio, 
									@QueryParam("domicilioLaboral") String domicilioLaboral, 
									@QueryParam("rut") String rut, 
									@QueryParam("fechaDeNacimiento") Date fechaDeNacimiento) {
		try {
			ControladoraUsuario.modificarUsuario(usrKey, usuarioUsado, usuario, contrasenia, nombre, cedula, email, tel, cel, domicilio, domicilioLaboral, rut, fechaDeNacimiento);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "completado";
	}

	@DELETE
	@Path("/eliminarUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public String eliminarUsuario(@QueryParam("usrKey") String usrKey, 
									@QueryParam("usuario") String usuario) {
		
		try {
			return ControladoraUsuario.eliminarUsuario(usrKey, usuario);
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@OPTIONS
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations(){
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}
}