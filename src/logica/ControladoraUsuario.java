package logica;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ControladoraUsuario {
	//PRINCIPIO SECCION CONSULTAS
	public static ArrayList<Usuario> obtenerUsuarios(String usuarioActual) throws Exception{
        //Se valida que la sesion sea valida
		String usr = validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		ControladoraPermiso.tienePermiso("OU", buscarUsuarioPrivate(usr).getId());
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();

        Query query = s.createQuery("from Usuario");
        List list = query.list();
        
        s.disconnect();
        
        if (list.isEmpty()){
        	return null;
        } else {
        	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        	for (Object o: list)
        		usuarios.add((Usuario)o);
        	return usuarios;
        }
	}
	
	public static boolean existeUsuario(String usuario) {
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();

        Query query = s.createQuery("from Usuario where usuario = :usuario ");
        query.setParameter("usuario", usuario);
        List list = query.list();
        
        s.disconnect();
        
        if (list.isEmpty()){
        	return false;
        } else {
        	return true;
        }
	}
	
	public static ArrayList<Usuario> obtenerNotificados(long idCaso) {
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();

        Query query = s.createQuery("select U from Usuario as U, UsuarioAsociadoACaso as UA where UA.idCaso = :idCaso and UA.tipo = 'profesional' or UA.tipo = 'funcionario'");
        query.setParameter("idCaso", idCaso);
        List list = query.list();
        
        s.disconnect();

        if (list.isEmpty()){
        	return null;
        } else {
        	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        	for (Object o: list)
        		usuarios.add((Usuario)o);
        	return usuarios;
        }
	}
	
	public static Usuario buscarUsuario(String usuarioActual, String usuario) throws Exception{
        //Se valida que la sesion sea valida
		validateUsrSession(usuarioActual);

		return buscarUsuarioPrivate(usuario);
	}
	
	private static Usuario buscarUsuarioPrivate(String usuario) {
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
		
        Query query = s.createQuery("from Usuario where usuario = :usuario ");
        query.setParameter("usuario", usuario);
        List list = query.list();
        
        s.disconnect();
        
        if(list.isEmpty()){
        	return null;
        }
        
        Usuario usr = (Usuario)list.get(0);
		
		return usr;
	}
	
	public static String login(String usuario, String contrasenia) throws Exception{
		//Validar usuario y contrasenia
		validateLogin(usuario, contrasenia);
		
        Usuario usr = buscarUsuarioPrivate(usuario);
		
		if (usr == null)
			throw new Exception("usuario no encontrado");
		
		if (!usr.getContrasenia().equals(contrasenia))
			throw new Exception("contrasenia incorrecta");
		
		return Utilidades.Encriptar(usr.getUsuario() + "-" + usr.getCedula() + "-" + usr.getContrasenia());
	}
	
	private static void validateLogin(String usuario, String contrasenia) throws Exception{
		String errores = "";
		if (!Validacion.validarUsuario(usuario))
			errores += "usuario";
		if (!Validacion.validarContrasenia(contrasenia))
			errores += "|contrasenia";
		
		if (errores != "")
			throw new Exception(errores);
	}
	//FIN SECCION CONSULTAS

	//PRINCIPIO SECCION ALTAS
	public static String AgregarUsuario(String usuarioActual, String usuario, String contrasenia, String nombre, String cedula, String email, 
			String tel, String cel, String domicilio, String domicilioLaboral, String rut, String fechaDeNacimiento) throws Exception {
        //Se valida que la sesion sea valida
		String usr = validateUsrSession(usuarioActual);
		
		//Se valida que el usuario que se intenta registrar no este duplicado
		if(ControladoraUsuario.existeUsuario(usuario))
			throw new Exception("duplicado");
			
		Usuario usrActual = buscarUsuarioPrivate(usr);
		//Se validan los permisos
		ControladoraPermiso.tienePermiso("AU", usrActual.getId());
			
		//Se valida que los datos sean validos
		validarDatosUsuario(usuario, contrasenia, nombre, cedula, email, tel, cel, domicilio, domicilioLaboral, fechaDeNacimiento);
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
		
        //Se ingresa un nuevo usuario
		Usuario u = new Usuario (usuario, contrasenia, nombre, cedula, email, tel, cel, domicilio, domicilioLaboral, rut, fechaDeNacimiento);
		
		//Se guarda el nuevo usuario en la base de datos
		s.save(u);
		s.getTransaction().commit();
        
		s.disconnect();
		
		ControladoraAuditoria.agregarAccion("Agregar Usuario " + usuario,usrActual.getId());
		
		return "completado";
	}
	
	private static void validarDatosUsuario (String usuario, String contrasenia, String nombre, String cedula, String email, String tel, String cel, 
			String domicilio, String domicilioLaboral, String fechaDeNacimiento) throws Exception {
		String errores = "";
		if(!Validacion.validarUsuario(usuario))
			errores += "usuario";
		if(!Validacion.validarContrasenia(contrasenia))
			errores += "|contrasenia";
		if(!Validacion.validarNombre(nombre))
			errores += "|nombre";
		if(!Validacion.validarCedula(cedula))
			errores += "|cedula";
		if(!Validacion.validarEmail(email))
			errores += "|email";
		if(!Validacion.validarTel(tel))
			errores += "|tel";
		if(!Validacion.validarCel(cel))
			errores += "|cel";
		if(!Validacion.validarDomicilio(domicilio))
			errores += "|domicilio";
		if(!Validacion.validarDomicilio(domicilioLaboral))
			errores += "|domicilioLaboral";
		if(!Validacion.validarFechaDeNacimiento(fechaDeNacimiento))
			errores += "|fechaDeNacimiento";
		
		if(errores != "")
			throw new Exception(errores);
	}
	
	public static String validateUsrSession(String usuarioActual) throws Exception {
		if (usuarioActual == "")
			throw new Exception("Session error");
		
		String usuarioActualDesencriptado = Utilidades.Desencriptar(usuarioActual);
		String[] parts = usuarioActualDesencriptado.split("-"); 
		String result = login(parts[0], parts[2]);
		if (result == "usuario" || result == "contrasenia")
			throw new Exception("Session error");
		
		return parts[0];
	}
	//FIN SECCION ALTAS
	
	//PRINCIPIO SECCION MODIFICACIONES
	public static String modificarUsuario(String usuarioActual, String usuarioUsado, String usuario, String contrasenia, String nombre, 
			String cedula, String email, String tel, String cel, String domicilio, String domicilioLaboral, String rut, 
			String fechaDeNacimiento) throws Exception{
		
		//Se valida que la sesion sea valida
		String usr = validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		ControladoraPermiso.tienePermiso("MU", buscarUsuarioPrivate(usr).getId());

        
        return modificarUsuarioPrivado(usuarioActual, usuarioUsado, usuario, contrasenia, nombre, cedula, email, tel, cel, domicilio, domicilioLaboral, 
        		rut, fechaDeNacimiento);
		
	}
	
	public static String modificarMiCuenta(String usuarioActual, String usuario, String contrasenia, String nombre, 
			String cedula, String email, String tel, String cel, String domicilio, String domicilioLaboral, String rut, 
			String fechaDeNacimiento) throws Exception{
		//Se valida que la sesion sea valida
		String usr = validateUsrSession(usuarioActual);
        
        return modificarUsuarioPrivado(usuarioActual, usr, usuario, contrasenia, nombre, cedula, email, tel, cel, domicilio, domicilioLaboral, 
        		rut, fechaDeNacimiento);
		
	}
	
	public static String modificarContrasenia(String usuarioActual, String contrasenia) throws Exception{
		//Se valida que la sesion sea valida
		String usr = validateUsrSession(usuarioActual);
		
		Usuario u = buscarUsuarioPrivate(usr);
		
		if(u == null) {
			return "not found";
		} else {
			//Se valida que los datos sean validos

			if(!Validacion.validarContrasenia(contrasenia))
				throw new Exception("contrasenia");
			
			u.setContrasenia(contrasenia);
	        
			//Se obtiene y empieza la session
			Session s = HibernateUtil.getSession();
	        s.beginTransaction();
	        
	        s.update(u); 
	        
			s.getTransaction().commit();
	        
			s.disconnect();
			
			return "completado";
		}
	}
	
	private static String modificarUsuarioPrivado(String usuarioActual, String usuarioUsado, String usuario, String contrasenia, String nombre, 
			String cedula, String email, String tel, String cel, String domicilio, String domicilioLaboral, String rut, 
			String fechaDeNacimiento) throws Exception{
        Usuario u = buscarUsuarioPrivate(usuarioUsado);
		
		if (u == null) {
			return "not found";	
		} else {
			//Se valida que los datos sean validos
			validarDatosUsuario(usuario, contrasenia, nombre, cedula, email, tel, cel, domicilio, domicilioLaboral, fechaDeNacimiento);
			
			//Se valida que en caso de haber cambiado el nombre de usuario, el nuevo nombre no este aun registrado
			if (!usuario.equals(usuarioUsado)) {
				Usuario usrUpdate = buscarUsuarioPrivate(usuario);
				
				if (usrUpdate == null) {
					u.setUsuario(usuario);
				} else {
					throw new Exception ("Duplicado");
				}
			}
			
			if(usuarioUsado != validateUsrSession(usuarioActual)){
				u.setContrasenia(contrasenia);
			}
			u.setNombre(nombre);
			u.setCedula(cedula);
			u.setEmail(email);
			u.setTel(tel);
			u.setCel(cel);
			u.setDomicilio(domicilio);
			u.setDomicilioLaboral(domicilioLaboral);
			u.setFechaDeNacimiento(fechaDeNacimiento);
	        
			//Se obtiene y empieza la session
			Session s = HibernateUtil.getSession();
	        s.beginTransaction();
	        
	        s.update(u); 
	        
			s.getTransaction().commit();
	        
			s.disconnect();
			if(usuarioUsado != validateUsrSession(usuarioActual)){
				ControladoraAuditoria.agregarAccion("Modificar Usuario " + usuario,buscarUsuarioPrivate(validateUsrSession(usuarioActual)).getId());
			}
			
			return "completado";
		}	
		
	}
	//FIN SECCION MODIFICACIONES
	
	//PRINCIPIO SECCION BAJAS
	public static String eliminarUsuario(String usuarioActual, String usuario) throws Exception {
		
		//Se valida que la sesion sea valida
		String usr = validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		ControladoraPermiso.tienePermiso("EU", buscarUsuarioPrivate(usr).getId());
        
        Usuario u = buscarUsuarioPrivate(usuario);
		
		if(u == null) {
			return "not found";	
		} else {
			//Se obtiene y empieza la session
			Session s = HibernateUtil.getSession();
	        s.beginTransaction();
	        
	        s.delete(u); 
	        
			s.getTransaction().commit();
	        
			s.disconnect();

			ControladoraAuditoria.agregarAccion("Eliminar Usuario " + usuario,buscarUsuarioPrivate(usr).getId());
			
			return "completado";
		}	
	}
	//FIN SECCION BAJAS
}
