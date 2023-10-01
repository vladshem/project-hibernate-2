package org.dao;

import org.hibernate.SessionFactory;
import org.entity.Inventory;
public class InventoryDAO extends GenericDAO<Inventory> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
}
