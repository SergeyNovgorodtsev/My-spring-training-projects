package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;



@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Customer> query =
                currentSession.createQuery("from Customer order by lastName",
                                                Customer.class);

        return query.getResultList();
    }

    @Override
    public void saveCustomer(Customer customer) {
        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        //save the customer
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();

        return currentSession.get(Customer.class, theId);
    }

    @Override
    public void deleteCustomer(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query query = currentSession.createQuery(
                "delete from Customer where id=:theCustomerId"
        );
        query.setParameter("theCustomerId", theId);

        query.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String name) {
        Session currentSession = sessionFactory.getCurrentSession();
        List<Customer> customers = null;
        if(name != null && name.trim().length() > 0) {
            Query<Customer> query = currentSession.createQuery(
                    "FROM Customer WHERE lower(lastName) like: name OR lower(firstName) like: name",
                    Customer.class
            );
            query.setParameter("name", "%" + name.toLowerCase() + "%");
            customers = query.getResultList();
        }
        return customers;
    }
}
