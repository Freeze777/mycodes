package centurylink.cpms;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {

	private static SessionFactory factory;
	
	public static void createConnection()
	{	
		//System.out.println("HibernateConnection-in createConnection");
		try {
			//System.out.println("within HibernateConnection try block");
		factory = ((AnnotationConfiguration) new AnnotationConfiguration()
		.configure()).addPackage("centurylink.cpms")
		.addAnnotatedClass(CpmsUser.class).addAnnotatedClass(CpmsRoutes.class).addAnnotatedClass(CpmsRequestLogs.class).addAnnotatedClass(CpmsTransits.class).addAnnotatedClass(CpmsSeats.class).buildSessionFactory();
//			Configuration cfg = new Configuration().addResource("CpmsUser.hbm.xml").addResource("CpmsRoutes.hbm.xml");
//			factory = cfg.buildSessionFactory();

} catch (Throwable ex) {
System.err.println("Failed to create sessionFactory object." + ex);
throw new ExceptionInInitializerError(ex);
}
//System.out.println("HibernateConnection-connection made successfully");

	}

	public static SessionFactory getFactory() {
		return factory;
	}



}
