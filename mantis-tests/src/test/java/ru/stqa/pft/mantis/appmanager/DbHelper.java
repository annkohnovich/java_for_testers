package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.stqa.pft.mantis.model.UserData;

import java.util.List;


public class DbHelper {
    private final SessionFactory sessionFactory;
    protected ApplicationManager app;

    public DbHelper(ApplicationManager app){
        this.app = app;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public UserData userWith(String email){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        UserData result = (UserData) session.createQuery(
                "select u " +
                        "from UserData u " +
                        "where u.email = '" + email + "'")
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List <UserData> users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List <UserData> list = session.createQuery("from UserData").list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
