package com.yesipov.dao;

import com.yesipov.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class StudentDao {
    private SessionFactory sessionFactory;

    public StudentDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void addStudent(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        }
    }

    public List<String> getStudentsByGroup(String groupName) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createSQLQuery("SELECT t1.studentname FROM Student as t1 " +
                            "JOIN student_group as t2 on t1.id = t2.student_id " +
                            "JOIN groups as t3 on t2.group_id = t3.groupId " +
                            "where lower(t3.groupName) = lower(:groupName)")
                    .setParameter("groupName", groupName)
                    .list();
        }
    }

    public List<String> getGroupsByStudentName(String studentName) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createSQLQuery("SELECT t1.groupname FROM groups as t1 " +
                            "JOIN student_group as t2 on t1.groupId = t2.group_id " +
                            "JOIN Student as t3 on t2.student_id = t3.id " +
                            "where lower(t3.studentName) = lower(:studentName)")
                    .setParameter("studentName", studentName)
                    .list();
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
