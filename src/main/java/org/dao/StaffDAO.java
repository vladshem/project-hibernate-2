package org.dao;

import org.hibernate.SessionFactory;
import org.entity.Staff;
public class StaffDAO extends GenericDAO<Staff> {
    public StaffDAO(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
