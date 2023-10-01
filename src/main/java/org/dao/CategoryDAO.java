package org.dao;

import org.hibernate.SessionFactory;
import org.entity.Category;

public class CategoryDAO extends GenericDAO<Category>{
    public CategoryDAO(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
