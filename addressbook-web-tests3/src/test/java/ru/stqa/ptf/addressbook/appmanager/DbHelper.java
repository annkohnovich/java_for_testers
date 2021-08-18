package ru.stqa.ptf.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import java.util.List;

public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

    }

    public Groups groups(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }


    public Contacts contacts(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public List <ContactData> contactsList(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

//    public List <GroupData> groupList(){
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        List<GroupData> result = session.createQuery("from GroupData").list();
//        session.getTransaction().commit();
//        session.close();
//        return result;
//    }

    public GroupData groupWithMaxId(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        GroupData result = (GroupData) session.createQuery(
                "select g " +
                        "from GroupData g " +
                        "where g.id = ( select max(gg.id) from GroupData gg ) ")
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }


}
