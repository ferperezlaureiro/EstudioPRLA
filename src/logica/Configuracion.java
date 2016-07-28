package logica;

import java.sql.Date;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;

public class Configuracion {
	public Configuracion () {
        BasicConfigurator.configure();
        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
        //Carga el usuario Master de prueba
        cargarUsuarioPrueba();
        //Carga los permisos
        crearPermisos();
        //Asigna todo los permisos a Master sin usar el metodo de la controladora porque ese metodo requiere permisos que no se tienen aun
        asignarPermisosAMaster();
	}
	

	private void cargarUsuarioPrueba() {
		//Se obtiene y empieza la session
		Session s = HibernateUtil.getSession();
        s.beginTransaction();
		
        //Se ingresa un nuevo usuario
        Usuario u = new Usuario("Master", "Master1!", "Master", "49290325", "master@gmail.com", "099954750", "091410102", 
				"Satint bois 5063", "Treinta y tres 1334", "123123122123", new Date(11,12,1992));
		
		//Se guarda el nuevo usuario en la base de datos
		s.save(u);
		s.getTransaction().commit();
		
        s.disconnect();
	}
	
	private void crearPermisos(){
		ControladoraPermiso.crearPermiso("Agregar Usuario", "AU");//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		ControladoraPermiso.crearPermiso("Eliminar Usuario", "EU");//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		ControladoraPermiso.crearPermiso("Modificar Usuario", "MU");
		ControladoraPermiso.crearPermiso("Agregar Caso", "AC");//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		ControladoraPermiso.crearPermiso("Eliminar Caso", "EC");//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		ControladoraPermiso.crearPermiso("Modificar Caso", "MC");
		ControladoraPermiso.crearPermiso("Agregar Involucrado", "AI");//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		ControladoraPermiso.crearPermiso("Eliminar Involucrado", "EI");//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		ControladoraPermiso.crearPermiso("Modificar Involucrado", "MI");
		ControladoraPermiso.crearPermiso("Asignar Permiso", "AP");//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		ControladoraPermiso.crearPermiso("Revocar Permiso", "RP");//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		ControladoraPermiso.crearPermiso("Obtener Usuarios", "OU");//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		ControladoraPermiso.crearPermiso("Obtener Todos los Casos", "OTC");//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
	}
	
	private void asignarPermisosAMaster(){
		asignarPermisoAP("AU", "Master");
		asignarPermisoAP("EU", "Master");
		asignarPermisoAP("MU", "Master");
		asignarPermisoAP("AC", "Master");
		asignarPermisoAP("EC", "Master");
		asignarPermisoAP("MC", "Master");
		asignarPermisoAP("AI", "Master");
		asignarPermisoAP("EI", "Master");
		asignarPermisoAP("MI", "Master");
		asignarPermisoAP("AP", "Master");
		asignarPermisoAP("RP", "Master");
		asignarPermisoAP("OU", "Master");
		asignarPermisoAP("OTC", "Master");
	}
	
	private void asignarPermisoAP(String code, String usuario){
		Usuario u = null;
		try {
			String usuarioActual = ControladoraUsuario.login(usuario, "Master1!");
			u = ControladoraUsuario.buscarUsuario(usuarioActual, usuario);
		} catch (Exception e) {
			
		}
		Permiso p = ControladoraPermiso.obtenerPermisoPorCode(code);
		
		Session s = HibernateUtil.getSession();
        s.beginTransaction();

        PermisoUsuario pU = new PermisoUsuario(p.getId(), u.getId());
        
        s.save(pU);
        s.getTransaction().commit();
        s.disconnect();
	}
}
