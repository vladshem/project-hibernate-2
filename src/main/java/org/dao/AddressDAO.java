package org.dao;

import org.hibernate.SessionFactory;
import org.entity.Address;

public class AddressDAO extends GenericDAO<Address> {
    public AddressDAO(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
