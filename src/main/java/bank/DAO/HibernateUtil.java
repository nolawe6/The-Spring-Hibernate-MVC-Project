package bank.DAO;
//package bankDAO;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//
//public class HibernateUtil {
//	private static final SessionFactory sessionFactory;
//	
//	static{
//		try{
//			Configuration config = new Configuration();
//			config.configure("hibernate.cfg.xml");
//			
//			sessionFactory = config.buildSessionFactory();
//		}catch(Throwable ex){
//			System.err.println("Initial Session Factory creation failed"+ ex);
//			throw new ExceptionInInitializerError(ex);
//		}
//	}
//	
//	public static SessionFactory getSessionFacotory(){
//		return sessionFactory;
//	}
//}
