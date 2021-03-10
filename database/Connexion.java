package database;

import models.Aimes;
import models.MemberShip;
import models.Membre;
import models.Page;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Properties;

public class Connexion {
    private static Session session = null ;
    private static SessionFactory sessionFactory = null ;
     private Transaction tx = null;
    public  Connexion(){
        Properties prop= new Properties();
        prop.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/SocialMedia");
        prop.setProperty("dialect", "org.hibernate.dialect.PostgreSQLDialect");
        prop.setProperty("hibernate.connection.username", "postgres");
        prop.setProperty("hibernate.connection.password", "123456");
        prop.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        prop.setProperty("show_sql","false");
        prop.setProperty("enable_lazy_load_no_trans","true");
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Membre .class);
        configuration.addAnnotatedClass(MemberShip.class);
        configuration.addAnnotatedClass(Page.class);
        configuration.addAnnotatedClass(Aimes.class);
        configuration.addProperties(prop);
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public Session getSession() {
        return session;
    }

    public void save(Object object)
    {
        tx = getSession().beginTransaction();
        getSession().save(object);
        tx.commit();
    }

    public Object find(Class c,Object object){
        return (Object) getSession().find(c,object);
    }

    public void closeConnexion(){
        getSession().close();
        sessionFactory.close();
    }



}
