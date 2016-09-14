package logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class ControladoraAuditoria {
	//PRINCIPIO SECCION ALTAS
	public static void agregarAccion(String accion, long idUsuario){
		//Se arman variables de fecha y hora actuales
        Calendar hoy = Calendar.getInstance();
		String fecha = "" + hoy.get(Calendar.DAY_OF_MONTH) + "/" + (hoy.get(Calendar.MONTH)+1)  + "/" + hoy.get(Calendar.YEAR);
		
		String hora = "";
		if(hoy.get(Calendar.MINUTE)<10){
			hora = "" + hoy.get(Calendar.HOUR_OF_DAY) + ":0" + hoy.get(Calendar.MINUTE);
		} else{
			hora = "" + hoy.get(Calendar.HOUR_OF_DAY) + ":" + hoy.get(Calendar.MINUTE);
		}
		
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();

        //Se ingresa un nueva accion
        Auditoria a = new Auditoria(hora, fecha, accion, idUsuario);
		
		//Se guarda la nueva accion en la base de datos
		s.save(a);
		s.getTransaction().commit();
        
		s.disconnect();
	}
	//FIN SECCION ALTAS
	
	//PRINCIPIO SECCION CONSULTAS
	public static ArrayList<Auditoria> obtenerAcciones(String usuarioActual) throws Exception{
        //Se valida que la sesion sea valida
		String usr = ControladoraUsuario.validateUsrSession(usuarioActual);
		
		//Se validan los permisos
		Usuario usrActual = ControladoraUsuario.buscarUsuario(usuarioActual, usr);
		ControladoraPermiso.tienePermiso("CON", usrActual.getId());
		
		Session s = HibernateUtil.getSession();
		
        Query query = s.createQuery("from Auditoria");
        List list = query.list();

        s.disconnect();
        
        if (!list.isEmpty()) {
        	ArrayList<Auditoria> acciones = new ArrayList<Auditoria>();
        	for (Object o: list)
        		acciones.add((Auditoria)o);
        	return acciones;
        } else
        	return null;
	}
	//FIN SECCION CONSULTAS
}
