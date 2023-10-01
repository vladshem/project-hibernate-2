package org.dao;

import org.hibernate.SessionFactory;
import org.entity.Customer;

public class CustomerDAO extends GenericDAO<Customer> {
    public CustomerDAO(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
