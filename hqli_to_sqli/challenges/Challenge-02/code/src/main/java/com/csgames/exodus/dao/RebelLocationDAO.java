package com.csgames.exodus.dao;

import com.csgames.exodus.model.RebelLocation;
import com.csgames.exodus.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.InvalidParameterException;
import java.util.List;


/**
 * Used by AdminController...
 */
public class RebelLocationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void createRebelLocation(RebelLocation rebelLocation) {
        final Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(rebelLocation);
        session.getTransaction().commit();

        return;
    }

    public List<RebelLocation> getRebelLocation(String country) throws InvalidParameterException {
        if (!HibernateUtil.isParameterSecure(country))
            throw new InvalidParameterException("Dangerous actions detected! ABORT !!");

        final Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery(String.format("FROM RebelLocation WHERE country LIKE '%%%s%%'", country));
        List<RebelLocation> rebelLocations = query.getResultList();
        session.getTransaction().commit();
        return rebelLocations;
    }
}
