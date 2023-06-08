package com.montrehack.app.dao;

import com.montrehack.app.model.User;
import com.montrehack.app.util.HibernateUtil;
import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.spi.QueryTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

        final Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("FROM User WHERE username = :username");
        query.setString("username", username);
        User user = (User)query.uniqueResult();
        session.getTransaction().commit();
        return user;
    }

    public boolean login(String username, String password, ModelMap model) throws InvalidParameterException {
        if (username.toUpperCase().contains("SLEEP") || password.toUpperCase().contains("SLEEP") ||
                username.toUpperCase().contains("BENCHMARK") || password.toUpperCase().contains("BENCHMARK"))
            throw new InvalidParameterException("This is not a time based....");

        final Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        String hql = String.format("FROM User WHERE username = '%s' AND password = '%s'", username, password);
        Query query = session.createQuery(hql);

        Map<String, Filter> mf = new HashMap<String, Filter>();
        Set<String> filterNames = sessionFactory.getSessionFactory().getDefinedFilterNames();
        for(String filterName : filterNames) {
            mf.put(filterName, session.getEnabledFilter(filterName));
        }

        QueryTranslator qt = sessionFactory.getSessionFactory().getSettings().getQueryTranslatorFactory().createQueryTranslator(hql, hql, mf, sessionFactory.getSessionFactory(),
                null);
        qt.compile((Map)null, false);
        model.addAttribute("query", qt.getSQLString());

        User user = (User)query.uniqueResult();
        session.getTransaction().commit();
        if (user == null)
            return false;
        return true;
    }

    public void updateUser(String username, String name, String email) throws InvalidParameterException {

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
