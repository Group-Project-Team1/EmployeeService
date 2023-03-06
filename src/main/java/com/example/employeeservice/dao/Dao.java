package com.example.employeeservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class Dao<T> {

    @Autowired
    SessionFactory sessionFactory;

    Class<T> typeParameterClass;

    public List<T> getAll() {
        List<T> ts = null;
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(typeParameterClass);
            Root<T> root = cq.from(typeParameterClass);
            cq.select(root);
            ts = session.createQuery(cq).getResultList();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ts.isEmpty()) ? null : ts;
    }

    public List<T> findAll() {
        Session session = sessionFactory.openSession();

        // LONG FORM
        // Get a CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Get a CriteriaQuery for the Category class
        CriteriaQuery<T> criteriaQuery = builder.createQuery(typeParameterClass);

        // Specify Criteria root
        criteriaQuery.from(typeParameterClass);

        // Execute query
        List<T> ts = session.createQuery(criteriaQuery).getResultList();

        // SHORT FORM ATTEMPT - which doesn't work (cast exception)...need to troubleshoot
//        List<T> ts = session.createQuery(
//                (CriteriaQuery<Gif>)session.getCriteriaBuilder().createQuery(typeParameterClass).from(typeParameterClass)
//        ).getResultList();

        session.close();
        return ts;
    }

    @Transactional
    public T getById(int id) {
        Session session;
        Optional<T> t = Optional.empty();
        try {
            session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(typeParameterClass);
            Root<T> root = cq.from(typeParameterClass);
            Predicate predicate = cb.equal(root.get("id"), id);
            cq.select(root).where(predicate);
            t = session.createQuery(cq).uniqueResultOptional();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return (t.isPresent()) ? t.get() : null;
//        return t;
    }

    public Optional<T> getItemById(int id) {
        System.out.println("2");
        Session session;
        Optional<T> t = Optional.empty();
        try {
            session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(typeParameterClass);
            Root<T> root = cq.from(typeParameterClass);
            Predicate predicate = cb.equal(root.get("id"), id);
            cq.select(root).where(predicate);
            t = session.createQuery(cq).uniqueResultOptional();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public void add(T t) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(t);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteById(T t) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
            session.delete(t);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(T t){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(t);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(T t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(t);
        session.getTransaction().commit();
        session.close();
    }



}
