package com.example.codeclan.pirateservice.repository.ships;

import com.example.codeclan.pirateservice.models.Pirate;
import com.example.codeclan.pirateservice.models.Ship;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class ShipRepositoryImpl implements ShipRepositoryCustom {

    @Autowired
    EntityManager entityManager;


    @Transactional
    public List<Pirate> getPiratesForShip(Ship ship){

        List<Pirate> results = null;

        Session session = entityManager.unwrap(Session.class);
        //session is query(hibernate session) we use, it use hibernate methods.

//        Criteria cr = session.createCriteria(Pirate.class);
//        cr.add(Restrictions.eq("ship", ship));
//        //first "ship is property in pirate class, second ship is the param we pass in this method
//        //hibernate will translates above two cr lines this in to sql
//
//        select abouve lines alt+cmd+t


        try {
            Criteria cr = session.createCriteria(Pirate.class);
            cr.add(Restrictions.eq("ship", ship));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return results;
    }

    @Transactional
    public Ship findShipByName(String name){
        Ship foundShip = null;
        Session session = entityManager.unwrap(Session.class);

        try {
            Criteria cr = session.createCriteria(Ship.class);
            cr.add(Restrictions.eq("name", name));
            foundShip = (Ship)cr.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return foundShip;
    }
}
