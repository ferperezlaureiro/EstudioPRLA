package logica;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class ControladoraPermiso {
	//PRINCIPIO SECCION CONSULTAS
	public static ArrayList<Permiso> obtenerPermisos() {
		Session s = HibernateUtil.getSession();
		
        Query query = s.createQuery("from Permiso");
        List list = query.list();
        
        s.disconnect();
        
        if(!list.isEmpty())
        	return null;
        
        ArrayList<Permiso> permisos = new ArrayList<Permiso>();
        for(Object o : list)
        	permisos.add((Permiso)o);
        
        return permisos;
	}
	
	public static Permiso obtenerPermisoPorCode(String codePermiso) {
		Session s = HibernateUtil.getSession();
		
        Query query = s.createQuery("from Permiso where code = :code ");
        query.setParameter("code", codePermiso);
        List list = query.list();

        s.disconnect();
        
        if (list.isEmpty())
        	return null;
        else
        	return (Permiso)list.get(0);
	}
	
	public static ArrayList<Permiso> obtenerPermisoPorUsuario(String usuarioActual, String usuario) throws Exception{
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);
		
		Session s = HibernateUtil.getSession();
		
		Usuario u = ControladoraUsuario.buscarUsuario(usuarioActual, usuario);

		//select C from Caso as C, UsuarioAsociadoACaso as U where U.idCaso = C.id and U.idUsuario = :idUsuario
        Query query = s.createQuery("select P from Permiso as P, PermisoUsuario as PU where P.id = PU.idPermiso and PU.idUsuario = :idU");
        query.setParameter("idU", u.getId());
        List list = query.list();

        s.disconnect();
        
        if (list.isEmpty())
        	return null;
        else {
        	ArrayList<Permiso> retorno = new ArrayList<Permiso>();
        	for (Object o: list)
        		retorno.add((Permiso)o);
        	return retorno;
        }
	}
	
	public static void tienePermiso(String codePermiso, Long idUsuario) throws Exception{
        Permiso p = obtenerPermisoPorCode(codePermiso);
        
        if (p == null)
        	throw new Exception("Accion no permitida");

		Session s = HibernateUtil.getSession();
		
        Query query = s.createQuery("from PermisoUsuario where idPermiso = :idP and idUsuario = :idU ");
        query.setParameter("idP", p.getId());
        query.setParameter("idU", idUsuario);

        s.disconnect();
		
        if (query.list().isEmpty())
        	throw new Exception("Accion no permitida");
	}
	//FIN SECCION CONSULTAS

	//PRINCIPIO SECCION ALTAS
	public static boolean crearPermiso (String nombre, String code) {
		if(obtenerPermisoPorCode(code) != null)
			return false;
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();

        Permiso p = new Permiso(nombre, code);
		
		//Se guarda el nuevo permiso en la base de datos
		s.save(p);
		s.getTransaction().commit();
		s.disconnect();
		
		return true;
	}
	
	public static boolean asignarPermiso(String usuarioActual, String codePermiso, String usuario) throws Exception{
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		ControladoraPermiso.tienePermiso("AP", ControladoraUsuario.buscarUsuario(usuarioActual, usr).getId());
		
		Usuario u = ControladoraUsuario.buscarUsuario(usuarioActual, usuario);
		if(u == null)
			return false;
		
		Permiso p = obtenerPermisoPorCode(codePermiso);
		if(p == null)
			return false;
		
		tienePermiso(codePermiso, u.getId());
		
		Session s = HibernateUtil.getSession();
        s.beginTransaction();

        PermisoUsuario pU = new PermisoUsuario(p.getId(), u.getId());
        
        s.save(pU);
        s.getTransaction().commit();
        s.disconnect();
        
        return true;
	}
	//FIN SECCION ALTAS

	//PRINCIPIO SECCION BAJAS
	public static boolean rebocarPermiso(String usuarioActual, String codePermiso, Long idUsuario) throws Exception{
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		ControladoraPermiso.tienePermiso("RP", ControladoraUsuario.buscarUsuario(usuarioActual, usr).getId());
		
        Permiso p = obtenerPermisoPorCode(codePermiso);
        
        if (p == null)
        	return false;

		Session s = HibernateUtil.getSession();
        s.beginTransaction();
		
        Query query = s.createQuery("from PermisoUsuario where idPermiso = :idP and idUsuario = :idU ");
        query.setParameter("idP", p.getId());
        query.setParameter("idU", idUsuario);

        if (query.list().isEmpty()) {
            s.disconnect();
        	return false;
        }
        
        PermisoUsuario pU = (PermisoUsuario) query.list().get(0);
        
        s.delete(pU);
        s.getTransaction().commit();
        s.disconnect();
        
        return true;
	}
	//FIN SECCION BAJAS
}
