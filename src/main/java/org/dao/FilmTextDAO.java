package org.dao;

import org.hibernate.SessionFactory;
import org.entity.FilmText;
public class FilmTextDAO extends GenericDAO<FilmText> {
    public FilmTextDAO(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
