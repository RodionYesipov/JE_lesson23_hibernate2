package com.yesipov.dao;

import com.yesipov.model.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class GroupDao {
    private SessionFactory sessionFactory;

    public GroupDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void addGroup(Group group) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(group);
            transaction.commit();
        }
    }

    public Group getGroup(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM Group WHERE id = :id ", Group.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
