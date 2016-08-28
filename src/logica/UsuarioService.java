package logica;

import java.sql.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/UsuarioService")
public class UsuarioService {
	
	Fachada fachada = Fachada.getInstancia();
	

	@GET
    @Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuarios() {
		String retorno = fachada.login("Master", "Master1!");
		return fachada.obtenerUsuario(retorno, "Master");
	}
}