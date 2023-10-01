package org.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;

public abstract class GenericDAO<T> {
    private final Class<T> clazz;
    private SessionFactory sessionFactory;

    public GenericDAO(Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }
    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    public  T getById(final int id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> getItems(int offset, int count) {
        Query query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        return query.getResultList();
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName(), clazz).list();
    }

    public T save(final T enity) {
        getCurrentSession().saveOrUpdate(enity);
        return enity;
    }

    public T update(final T enity) {
        return (T) getCurrentSession().merge(enity);
    }

    public void delete(final T enity) {
        getCurrentSession().delete(enity);
    }

    public void deleteById(final int enityId) {
        final T entity = getById(enityId);
        delete(entity);
    }

}
