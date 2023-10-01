package org.dao;

import org.hibernate.SessionFactory;
import org.entity.Language;
public class LanguageDAO extends GenericDAO<Language> {
    public LanguageDAO(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
