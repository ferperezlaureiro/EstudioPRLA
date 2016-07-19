package logica;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class ControladoraPermiso {
	//PRINCIPIO SECCION CONSULTAS
	public static boolean tienePermiso(String codePermiso, Long idUsuario) {
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
		
        Query query = s.createQuery("from Permiso where code = :code ");
        query.setParameter("code", codePermiso);
        List list = query.list();
        
        Permiso p = (Permiso)list.get(0);
		
        query = s.createQuery("from PermisoUsuario where idPermiso = :idP and idUsuario = :idU ");
        query.setParameter("idP", p.getId());
        query.setParameter("idU", idUsuario);
        list = query.list();
        
        s.getTransaction().commit();
		
        PermisoUsuario pU = (PermisoUsuario) list.get(0);
        
        if (pU == null) {
        	return false;
        } else {
        	return true;
        }
	}
	//FIN SECCION CONSULTAS

	//PRINCIPIO SECCION ALTAS
	public static void crearPermiso (String nombre, String code) {
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();

        Permiso p = new Permiso(nombre, code);
		
		//Se guarda el nuevo permiso en la base de datos
		s.save(p);
		s.getTransaction().commit();
	}
	
	public static void asignarPermiso(String codePermiso, Long idUsuario) {
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
		
        Query query = s.createQuery("from Permiso where code = :code ");
        query.setParameter("code", codePermiso);
        List list = query.list();
        
        Permiso p = (Permiso)list.get(0);

        PermisoUsuario pU = new PermisoUsuario(p.getId(), idUsuario);
        
        s.save(pU);
        s.getTransaction().commit();
	}
	//FIN SECCION ALTAS

	//PRINCIPIO SECCION BAJAS
	public static void rebocarPermiso(String codePermiso, Long idUsuario) {
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
		
        Query query = s.createQuery("from Permiso where code = :code ");
        query.setParameter("code", codePermiso);
        List list = query.list();
        
        Permiso p = (Permiso)list.get(0);
		
        query = s.createQuery("from PermisoUsuario where idPermiso = :idP and idUsuario = :idU ");
        query.setParameter("idP", p.getId());
        query.setParameter("idU", idUsuario);
        list = query.list();
        
        PermisoUsuario pU = (PermisoUsuario) list.get(0);
        
        s.delete(pU);
        s.getTransaction().commit();
	}
	//FIN SECCION BAJAS
}
