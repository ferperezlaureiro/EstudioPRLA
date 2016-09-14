package logica;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration().addPackage("models").addAnnotatedClass(Usuario.class);
            configuration.addPackage("models").addAnnotatedClass(Caso.class);
            configuration.addPackage("models").addAnnotatedClass(Involucrado.class);
            configuration.addPackage("models").addAnnotatedClass(Mensaje.class);
            configuration.addPackage("models").addAnnotatedClass(Permiso.class);
            configuration.addPackage("models").addAnnotatedClass(PermisoUsuario.class);
            configuration.addPackage("models").addAnnotatedClass(UsuarioAsociadoACaso.class);
            configuration.addPackage("models").addAnnotatedClass(Auditoria.class);
    		configuration.configure("hibernate.cfg.xml");
    		sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
}