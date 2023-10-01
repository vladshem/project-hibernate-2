package org.dao;

import org.hibernate.SessionFactory;
import org.entity.Actor;

public class ActorDAO extends GenericDAO<Actor> {
    public ActorDAO(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
