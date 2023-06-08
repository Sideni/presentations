package com.csgames.exodus.dao;

import com.csgames.exodus.model.User;
import com.csgames.exodus.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.InvalidParameterException;

import static org.springframework.security.crypto.bcrypt.BCrypt.checkpw;


/**
 * Mostly used by IndexController and PanelController...
 */
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void createUser(User user) {
        final Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();

        return;
    }

    public User getUser(String username) throws InvalidParameterException {
        if (!HibernateUtil.isParameterSecure(username))
            throw new InvalidParameterException("Dangerous actions detected! ABORT !!");

        final Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("FROM User WHERE username = :username");
        query.setString("username", username);
        User user = (User)query.uniqueResult();
        session.getTransaction().commit();
        return user;
    }

    public boolean login(String username, String password) throws InvalidParameterException {
        if (!HibernateUtil.isParameterSecure(username) || !HibernateUtil.isParameterSecure(password))
            throw new InvalidParameterException("Dangerous actions detected! ABORT !!");

        final Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("FROM User WHERE username = :username");
        query.setString("username", username);
        User user = (User)query.uniqueResult();
        session.getTransaction().commit();
        if (user == null)
            return false;
        return checkpw(password, user.getPassword());
    }

    public void updateUser(String username, String name, String email) throws InvalidParameterException {
        if (!HibernateUtil.isParameterSecure(username) || !HibernateUtil.isParameterSecure(name) || !HibernateUtil.isParameterSecure(email))
            throw new InvalidParameterException("Dangerous actions detected! ABORT !!");

        final Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        final Query query = session.createQuery("UPDATE User SET name = :name, email = :email WHERE username = :username");
        query.setString("name", name);
        query.setString("email", email);
        query.setString("username", username);
        query.executeUpdate();
        session.getTransaction().commit();
    }
}
