package logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import consultaIUEwsdl.ConsultaMain;

public class ControladoraCaso {
	//PRINCIPIO SECCION CONSULTAS
	public static ArrayList<Caso> obtenerCasos (String usuarioActual) throws Exception {
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		ControladoraPermiso.tienePermiso("OTC", ControladoraUsuario.buscarUsuario(usuarioActual, usr).getId());
			
		Session s = HibernateUtil.getSession();
		
        Query query = s.createQuery("from Caso");
        List list = query.list();

        s.disconnect();
        
        if (!list.isEmpty()) {
        	ArrayList<Caso> casos = new ArrayList<Caso>();
        	for (Object o: list)
        		casos.add((Caso)o);
        	return casos;
        } else
        	throw new Exception ("No hay casos");
	}

	public static ArrayList<Caso> obtenerCasosPorUsuario (String usuarioActual, String usuario) throws Exception {
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);

		Usuario u = ControladoraUsuario.buscarUsuario(usuarioActual, usuario);
		
		if (u == null)
			throw new Exception("Usuario no encontrado");
		
		Session s = HibernateUtil.getSession();
		
        Query query = s.createQuery("select C from Caso as C, UsuarioAsociadoACaso as U where U.idCaso = C.id and U.idUsuario = :idUsuario");
        query.setParameter("idUsuario", u.getId());
        List list = query.list();

        s.disconnect();
        
        if (!list.isEmpty()) {
        	ArrayList<Caso> casos = new ArrayList<Caso>();
        	for (Object o: list)
        		casos.add((Caso)o);
        	return casos;
        } else
        	throw new Exception ("No hay casos asignados a este usuario");
	}


	public static ArrayList<Caso> obtenerCasosNoAsignadosAUsuario (String usuarioActual, String usuario) throws Exception {
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);

		Usuario u = ControladoraUsuario.buscarUsuario(usuarioActual, usuario);
		
		if (u == null)
			throw new Exception("Usuario no encontrado");
		
		Session s = HibernateUtil.getSession();
        Query query = s.createQuery("from Caso where id not in(select U.idCaso from UsuarioAsociadoACaso as U where U.idUsuario = :idUsuario)");
        query.setParameter("idUsuario", u.getId());
        List list = query.list();

        s.disconnect();
        
        if (!list.isEmpty()) {
        	ArrayList<Caso> casos = new ArrayList<Caso>();
        	for (Object o: list)
        		casos.add((Caso)o);
        	return casos;
        } else
        	throw new Exception ("Todos los casos estan asignados a este usuario");
	}

	public static ArrayList<Usuario> obtenerUsuariosPorCaso(String usuarioActual, String iUE) throws Exception {
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);

		Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
		
		if (c == null)
			throw new Exception("Caso no encontrado");
		
		Session s = HibernateUtil.getSession();
		
        Query query = s.createQuery("select U from Usuario as U, UsuarioAsociadoACaso as UA where U.id = UA.idUsuario and UA.idCaso = :idCaso");
        query.setParameter("idCaso", c.getId());
        List list = query.list();

        s.disconnect();
        
        if (!list.isEmpty()) {
        	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        	for (Object o: list)
        		usuarios.add((Usuario)o);
        	return usuarios;
        } else
        	throw new Exception ("No hay usuarios asignados a este caso");
	}

	public static ArrayList<Usuario> obtenerUsuariosNoAsignadosACaso(String usuarioActual, String iUE) throws Exception {
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		ControladoraPermiso.tienePermiso("ADUC", ControladoraUsuario.buscarUsuario(usuarioActual, usr).getId());

		Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
		
		if (c == null)
			throw new Exception("Caso no encontrado");
		
		Session s = HibernateUtil.getSession();
		
        Query query = s.createQuery("from Usuario where id not in (select UA.idUsuario from UsuarioAsociadoACaso as UA where UA.idCaso = :idCaso)");
        query.setParameter("idCaso", c.getId());
        List list = query.list();

        s.disconnect();
        
        if (!list.isEmpty()) {
        	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        	for (Object o: list)
        		usuarios.add((Usuario)o);
        	return usuarios;
        } else
        	throw new Exception ("Todos los usuarios estan asignados a este caso");
	}

	public static Caso obtenerCasoPorIUE (String usuarioActual, String iUE) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se valida el formato del IUE
		if (!Validacion.validarIUE(iUE))
			throw new Exception("IUE");
			
		Session s = HibernateUtil.getSession();
		
        Query query = s.createQuery("from Caso where IUE = :iUE ");
        query.setParameter("iUE", iUE);
        List list = query.list();

        s.disconnect();
        
        if (!list.isEmpty())
        	return (Caso)list.get(0);
        else
        	return null;
	}
	
	public static boolean existeCaso (String usuarioActual, String iUE) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
				
		Session s = HibernateUtil.getSession();
		
        Query query = s.createQuery("from Caso where IUE = :iUE ");
        query.setParameter("iUE", iUE);

        s.disconnect();
        
        if (!query.list().isEmpty())
        	return true;
        else
        	return false;
	}
	
	public static ArrayList<Mensaje> obtenerConversacion (String usuarioActual, String iUE) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se valida el formato del IUE
		if (!Validacion.validarIUE(iUE))
			throw new Exception("IUE");
        
        Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
        if(c != null) {
    		Session s = HibernateUtil.getSession();
    		
        	Query query = s.createQuery("from Mensaje where idCaso = :idCaso ");
            query.setParameter("idCaso", c.getId());
            List list = query.list();
            
            s.disconnect();
            
            if(!list.isEmpty()) {
            	ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
            	for(Object o: list) {
            		mensajes.add((Mensaje)o);
            	}
            	return mensajes;
            } else {
            	throw new Exception("No hay mensajes para este caso");
            }
        } else {
        	throw new Exception("Caso no encontrado");
        }
	}
	
	public static ArrayList<Involucrado> obtenerInvolucrados (String usuarioActual, String iUE) throws Exception {
		ArrayList<Involucrado> involucrados = new ArrayList<>();
        
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se valida el formato del IUE
		if (!Validacion.validarIUE(iUE))
			throw new Exception("IUE");
		
		Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
        
		if(c != null) {
        	Session s = HibernateUtil.getSession();
    		
            Query query = s.createQuery("from Involucrado where idCaso = :idCaso ");
            query.setParameter("idCaso", c.getId());
            List list = query.list();

            s.disconnect();
            
            if(!list.isEmpty()) {
            	for(Object o: list) {
            		involucrados.add((Involucrado)o);
            	}
            	return involucrados;
            } else {
            	throw new Exception("No hay involucrados para este caso");
            }
        } else {
        	throw new Exception("Caso no encontrado");
        }
	}
	

	
	public static boolean existeInvolucrado (String usuarioActual, String iUE, String cedula) throws Exception{
		Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
        
		Session s = HibernateUtil.getSession();
		
        Query query = s.createQuery("from Involucrado where idCaso = :idCaso and cedula = :cedula");
        query.setParameter("idCaso", c.getId());
        query.setParameter("cedula", cedula);
        List list = query.list();

        s.disconnect();
        
        if (!list.isEmpty())
        	return true;
        else
        	return false;
	}
	
	public static Involucrado obtenerInvolucrado (String usuarioActual, String iUE, String cedula) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);

		Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
        
		if (c != null) {
			Session s = HibernateUtil.getSession();
    		
            Query query = s.createQuery("from Involucrado where idCaso = :idCaso and cedula = :cedula");
            query.setParameter("idCaso", c.getId());
            query.setParameter("cedula", cedula);
            List list = query.list();

            s.disconnect();
            
            if (!list.isEmpty())
            	return (Involucrado)list.get(0);
            else
            	throw new Exception("Involucrado no encontrado");
		} else {
        	throw new Exception("Caso no encontrado");
        }
	}
	
	public static void notificarMovimientos(String usuarioActual) throws Exception {
		//Se valida que la sesion sea valida
		ControladoraUsuario.validateUsrSession(usuarioActual);

		Session s = HibernateUtil.getSession();
		
		Query query = s.createQuery("from Caso where suscrito = true");
		List list = query.list();
		
		if (!list.isEmpty()) {
			ArrayList<Caso> casosSuscriptos = new ArrayList<Caso>();
			for(Object o : list){
				casosSuscriptos.add((Caso)o);
			}
			
			String[] aux = null;
			ArrayList<Usuario> usuarios = null;
			String[] mensage = null;
			
			for (Caso caso : casosSuscriptos) {
				aux = ConsultaMain.TieneMovimiento(caso.getIUE());
				if (aux != null) {
					usuarios = ControladoraUsuario.obtenerNotificados(caso.getId());
					if (usuarios != null) {
						for (Usuario u : usuarios) {
							for (int i = 0; i<aux.length; i++) {
								mensage = aux[i].split("&");
								ConsultaMain.SendMail(u.getEmail(), 
														"Nuevo movimiento (" + mensage[2] + ") de " + 
														caso.getCaratulado(),
														"IUE: " + caso.getIUE() + "</br>" + 
														"Caratulado: " + caso.getCaratulado() + "</br>" +
														"Tipo de movimiento: " + mensage[2] + "</br>" +
														"Decreto: " + mensage[0] + "</br>" + 
														"Vencimiento: " + mensage[3] + "</br>" + 
														"Fecha: " + mensage[1]);
							}
						}
					}
				}
			}
		}
	}
	//FIN SECCION CONSULTAS
	
	//PRINCIPIO SECCION INSERCIONES
	public static String agregarCaso(String usuarioActual, String iUE, String juzgado, int turno, String caratulado, boolean suscrito) throws Exception {
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		Usuario usrActual = ControladoraUsuario.buscarUsuario(usuarioActual, usr);
		ControladoraPermiso.tienePermiso("AC", usrActual.getId());
		
		if (existeCaso(usuarioActual, iUE))
			throw new Exception("duplicado");
		
		//Se valida que los datos sean validos
		validarDatosCaso(iUE, juzgado, turno, caratulado);
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();

        Caso c = new Caso(iUE, juzgado, turno, caratulado, suscrito);
		
		//Se guarda el nuevo caso en la base de datos
		s.save(c);
		s.getTransaction().commit();

        s.disconnect();

		ControladoraAuditoria.agregarAccion("Caso creado " + iUE, usrActual.getId());
        
        return "completado";
	}
	
	private static void validarDatosCaso(String iUE, String juzgado, int turno, String caratulado) throws Exception{
		String errores = "";
		if(!Validacion.validarIUE(iUE))
			errores += "IUE";
		if(!Validacion.validarDosPalabras(juzgado))
			errores += "|juzgado";
		if(!Validacion.validarTurno(turno))
			errores += "|turno";
		if(!Validacion.validarCaratulado(caratulado))
			errores += "|caratulado";
		if(errores != "") {
			throw new Exception(errores);
		}
	}
	
	public static String agregarInvolucrado(String usuarioActual, String iUE, String fechaDeNacimiento, String nombre, String cedula, 
			String nacionalidad, String domicilio,String clase) throws Exception {
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		Usuario usrActual = ControladoraUsuario.buscarUsuario(usuarioActual, usr);
		ControladoraPermiso.tienePermiso("AI", usrActual.getId());
		
		//Se valida que el involucrado no este duplicado
		if (existeInvolucrado(usuarioActual, iUE, cedula))
			throw new Exception("duplicado");
		
		//Se valida que los datos sean validos
		validarDatosInvolucrado(iUE, fechaDeNacimiento, nombre, cedula, nacionalidad, domicilio, clase);
		
		Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
		
		if(c != null) {
			//Se obtiene y empieza la session
			Session s = HibernateUtil.getSession();
	        s.beginTransaction();

	        Involucrado i = new Involucrado(c.getId(), fechaDeNacimiento, nombre, cedula, nacionalidad, domicilio, clase);
			
			//Se guarda el nuevo involucrado en la base de datos
			s.save(i);
			
			s.getTransaction().commit();	

	        s.disconnect();	

			ControladoraAuditoria.agregarAccion("Involucrado agregado " + cedula, usrActual.getId());
	        
	        return "completado";
		} else {
			throw new Exception("Caso no encontrado");
		}
	}
	
	public static void validarDatosInvolucrado(String iUE, String fechaDeNacimiento, String nombre, String cedula, 
			String nacionalidad, String domicilio,String clase) throws Exception {
		String errores = "";
		if(!Validacion.validarIUE(iUE))
			errores += "IUE";
		if(!Validacion.validarFechaDeNacimiento(fechaDeNacimiento))
			errores += "|fechaDeNacimiento";
		if(!Validacion.validarNombre(nombre))
			errores += "|nombre";
		if(!Validacion.validarCedula(cedula))
			errores += "|cedula";
		if(!Validacion.validarDosPalabras(nacionalidad))
			errores += "|nacionalidad";
		if(!Validacion.validarDomicilio(domicilio))
			errores += "|domicilio";
		if(!Validacion.validarDosPalabras(clase))
			errores += "|clase";
		
		if (errores != "") {
			throw new Exception(errores);
		}
	}
	
	public static String agregarMensaje(String usuarioActual, String iUE, String contenido) throws Exception {
		//Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);
		
		validarDatosMensaje(iUE, contenido);
		
		Usuario usuario = ControladoraUsuario.buscarUsuario(usuarioActual, usr);
		if (usuario == null)
			throw new Exception("Usuario no encontrado");
		
		Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
		if(c == null)
			throw new Exception("Caso no encontrado");	
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
        
        Calendar hoy = Calendar.getInstance();
		String fecha = "" + hoy.get(Calendar.DAY_OF_MONTH) + "/" + (hoy.get(Calendar.MONTH)+1)  + "/" + hoy.get(Calendar.YEAR);
		
		String hora = "";
		if(hoy.get(Calendar.MINUTE)<10){
			hora = "" + hoy.get(Calendar.HOUR_OF_DAY) + ":0" + hoy.get(Calendar.MINUTE);
		} else{
			hora = "" + hoy.get(Calendar.HOUR_OF_DAY) + ":" + hoy.get(Calendar.MINUTE);
		}
        Mensaje m = new Mensaje(c.getId(), usuario.getId(), fecha, hora, contenido);
		
		//Se guarda el nuevo involucrado en la base de datos
		s.save(m);
		s.getTransaction().commit();	
		
		s.disconnect();
		
		return "completado";
	}
	
	public static void validarDatosMensaje (String iUE, String contenido) throws Exception {
		String errores = "";
		if(!Validacion.validarIUE(iUE))
			errores += "IUE";
		if(!Validacion.validarContenidoMensaje(contenido))
			errores += "|contenido";
		
		if (errores != "") {
			throw new Exception (errores);
		}
	}
	
	public static String asociarUsuarioACaso (String usuarioActual, String usuario, String iUE, String tipo) throws Exception {
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		Usuario usrActual = ControladoraUsuario.buscarUsuario(usuarioActual, usr);
		ControladoraPermiso.tienePermiso("ADUC", usrActual.getId());
        
		Usuario u = ControladoraUsuario.buscarUsuario(usuarioActual, usuario);
		
		if (u == null)
			throw new Exception("Usuario no encontrado");
		
		Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
		
		if(c == null)
			throw new Exception("Caso no encontrado");	
		
		if (!Validacion.validarTipoUsuario(tipo))
			throw new Exception("tipo");
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();

        UsuarioAsociadoACaso ua = new UsuarioAsociadoACaso(u.getId(), c.getId(), tipo);
		//Se guarda la nueva asociacion de un usuario a un caso en la base de datos
		s.save(ua);
		s.getTransaction().commit();	
		
		s.disconnect();
		
		ControladoraAuditoria.agregarAccion("Usuario " + usuario + " agregado a caso " + iUE, usrActual.getId());
		
		return "completado";
	}
	//FIN SECCION INSERCIONES
	
	//PRINCIPIO SECCION MODIFICACIONES

	public static String modificarCaso(String usuarioActual, String iUEUsado, String iUE, String juzgado, int turno, String caratulado, 
										boolean suscrito) throws Exception {
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		Usuario usrActual = ControladoraUsuario.buscarUsuario(usuarioActual, usr);
		ControladoraPermiso.tienePermiso("MC", usrActual.getId());
		
		Caso c = obtenerCasoPorIUE(usuarioActual, iUEUsado);
		
		if (c == null) {
			throw new Exception ("not found");
		} else {
			//Se valida que los datos sean validos
			validarDatosCaso(iUE, juzgado, turno, caratulado);
			
			if (!iUE.equals(iUEUsado)) {
				Caso casoUpdate = obtenerCasoPorIUE(usuarioActual, iUE);
				
				if (casoUpdate == null) {
					c.setIUE(iUE);
				} else {
					throw new Exception("Duplicado");
				}
			}
			
			c.setJuzgado(juzgado);
			c.setTurno(turno);
			c.setCaratulado(caratulado);
			c.setSuscrito(suscrito);

			//Se obtiene y empieza la session
			Session s = HibernateUtil.getSession();
	        s.beginTransaction();
	        
			//Se modifica el caso en la base de datos
			s.update(c);
			s.getTransaction().commit();

	        s.disconnect();
			
			ControladoraAuditoria.agregarAccion("Caso modificado " + iUE, usrActual.getId());
	        
	        return "completado";
		}		
	}
	
	public static String modificarInvolucrado(String usuarioActual, String iUE, String cedulaUsada, String fechaDeNacimiento, String nombre, 
			String cedula, String nacionalidad, String domicilio,String clase) throws Exception {
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		Usuario usrActual = ControladoraUsuario.buscarUsuario(usuarioActual, usr);
		ControladoraPermiso.tienePermiso("MI", usrActual.getId());

		//Se valida que los datos sean validos
		validarDatosInvolucrado(iUE, fechaDeNacimiento, nombre, cedula, nacionalidad, domicilio, clase);
		
		Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
		

        if(c == null) 
        	throw new Exception ("Caso no encontrado");
    
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
		
        Query query = s.createQuery("from Involucrado where idCaso = :idCaso and cedula = :ciInvolucrado");
        query.setParameter("idCaso", c.getId());
        query.setParameter("ciInvolucrado", cedulaUsada);
		
		if(query.list().isEmpty())
			throw new Exception("involucrado no encontrado");	
		
        Involucrado i = (Involucrado)query.list().get(0);
		
        if (cedulaUsada != cedula) {
    		query = s.createQuery("from Involucrado where idCaso = :idCaso and cedula = :ciInvolucrado");
            query.setParameter("idCaso", c.getId());
            query.setParameter("ciInvolucrado", cedula);
    		
    		if(!query.list().isEmpty())
    			throw new Exception("Duplicado");
    		
    		i.setCedula(cedula);
        }	
		
		i.setFechaDeNacimiento(fechaDeNacimiento);
		i.setNombre(nombre);
		i.setCedula(cedula);
		i.setNacionalidad(nacionalidad);
		i.setDireccion(domicilio);
		i.setClase(clase);
		

        s.beginTransaction();
        
        s.update(i); 
        
		s.getTransaction().commit();
		
		s.disconnect();
		
		ControladoraAuditoria.agregarAccion("Involucrado " + cedula + " del caso " + iUE + " modificado", usrActual.getId());
		
		return "completado";
	}
	
	public static String suscribirCaso (String usuarioActual, String iUE) throws Exception{
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		ControladoraPermiso.tienePermiso("MC", ControladoraUsuario.buscarUsuario(usuarioActual, usr).getId());
		
		Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
		
		if (c == null) 
        	throw new Exception ("Caso no encontrado");
		
		c.setSuscrito(true);

		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
        
		//Se modifica el caso en la base de datos
		s.update(c);
		s.getTransaction().commit();

        s.disconnect();
        
        return "completado";
	}
	
	public static String dessuscribirCaso (String usuarioActual, String iUE) throws Exception{
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		ControladoraPermiso.tienePermiso("MC", ControladoraUsuario.buscarUsuario(usuarioActual, usr).getId());
		
		Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
		
		if (c == null) 
        	throw new Exception ("Caso no encontrado");
		
		c.setSuscrito(false);

		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
        
		//Se modifica el caso en la base de datos
		s.update(c);
		s.getTransaction().commit();

        s.disconnect();
        
        return "completado";
	}
	//FIN SECCION MODIFICACIONES
	
	//PRINCIPIO SECCION BAJAS
	public static String eliminarCaso(String usuarioActual, String iUE) throws Exception{
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		Usuario usrActual = ControladoraUsuario.buscarUsuario(usuarioActual, usr);
		ControladoraPermiso.tienePermiso("EC", usrActual.getId());
	        
	    Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
		
		if(c == null)
			throw new Exception("Caso no encontrado");	
		
        s.beginTransaction();
        
        eliminarInvolucradosPorCaso(c.getId());
        eliminarMensajesPorCaso(c.getId());
        eliminarAsignaciones(c.getId());
        
        s.delete(c); 
        
		s.getTransaction().commit();
		
		s.disconnect();
		
		ControladoraAuditoria.agregarAccion("Eliminado caso " + iUE, usrActual.getId());
		
		return "completado";
	}
	
	private static void eliminarAsignaciones(long idCaso){
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();

        s.beginTransaction();
        
        Query query = s.createQuery("from UsuarioAsociadoACaso where idCaso = :idCaso");
        query.setParameter("idCaso", idCaso);
        List list = query.list();
		UsuarioAsociadoACaso u = null;
		if(!list.isEmpty()) {
	        for(Object o : list){
	        	u = (UsuarioAsociadoACaso)o;
		        s.delete(u); 
	        }
		}
        
		s.getTransaction().commit();
		
		s.disconnect();
	}
	
	private static void eliminarInvolucradosPorCaso(long idCaso) {
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();

        s.beginTransaction();
        
        Query query = s.createQuery("from Involucrado where idCaso = :idCaso");
        query.setParameter("idCaso", idCaso);
        List list = query.list();
		Involucrado i = null;
		if(!list.isEmpty()) {
	        for(Object o : list){
	        	i = (Involucrado)o;
		        s.delete(i); 
	        }
		}
        
		s.getTransaction().commit();
		
		s.disconnect();
	}
	
	private static void eliminarMensajesPorCaso(long idCaso) {
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();

        s.beginTransaction();
        
        Query query = s.createQuery("from Mensaje where idCaso = :idCaso");
        query.setParameter("idCaso", idCaso);
        List list = query.list();
		Mensaje m = null;
		if(!list.isEmpty()) {
	        for(Object o : list){
	        	m = (Mensaje)o;
		        s.delete(m); 
	        }
		}
        
		s.getTransaction().commit();
		
		s.disconnect();
	}
	
	public static String eliminarInvolucrado(String usuarioActual, String iUE, String ciInvolucrado) throws Exception{
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		Usuario usrActual = ControladoraUsuario.buscarUsuario(usuarioActual, usr);
		ControladoraPermiso.tienePermiso("EI", usrActual.getId());
	        
	    Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
		
        if(c == null) 
        	throw new Exception ("Caso no encontrado");
    
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
		
        Query query = s.createQuery("from Involucrado where idCaso = :idCaso and cedula = :ciInvolucrado");
        query.setParameter("idCaso", c.getId());
        query.setParameter("ciInvolucrado", ciInvolucrado);
        List list = query.list();
        Involucrado i = (Involucrado)list.get(0);
		
		if(i == null)
			throw new Exception("Involucrado no encontrado");	
	
        s.beginTransaction();
        
        s.delete(i); 
        
		s.getTransaction().commit();
		
		s.disconnect();
		
		ControladoraAuditoria.agregarAccion("Eliminado involucrado " + ciInvolucrado + " de caso " + iUE, usrActual.getId());
		
		return "completado";
	}
	
	public static String desasociarUsuarioACaso (String usuarioActual, String usuario, String iUE) throws Exception {
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		Usuario usrActual = ControladoraUsuario.buscarUsuario(usuarioActual, usr);
		ControladoraPermiso.tienePermiso("ADUC", usrActual.getId());
        
		Usuario u = ControladoraUsuario.buscarUsuario(usuarioActual, usuario);
		
		if (u == null)
			throw new Exception("Usuario no encontrado");
		
		Caso c = obtenerCasoPorIUE(usuarioActual, iUE);
		
		if(c == null)
			throw new Exception("Caso no encontrado");	
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();


        Query query = s.createQuery("from UsuarioAsociadoACaso where idCaso = :idCaso and idUsuario = :idUsuario");
        query.setParameter("idCaso", c.getId());
        query.setParameter("idUsuario", u.getId());
        List list = query.list();
        
        if (list == null)
        	throw new Exception("Usuario no asociado al caso");
        
        UsuarioAsociadoACaso ua = (UsuarioAsociadoACaso) list.get(0);
        //Se elimina el registro del usuario asociado al caso
		s.delete(ua);
		s.getTransaction().commit();	
		
		s.disconnect();
		
		ControladoraAuditoria.agregarAccion("Desasociar usuario " + usuario + " de caso " + iUE, usrActual.getId());
		
		return "completado";
	}
	//FIN SECCION BAJAS
}
