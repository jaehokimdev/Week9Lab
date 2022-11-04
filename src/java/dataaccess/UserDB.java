/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Role;
import models.User;
import dataaccess.RoleDB;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import services.RoleService;

/**
 *
 * @author joekim
 */
public class UserDB {
    
    public List<User> getAll() throws Exception {
        EntityManager em = DBUtil.getEmfactory().createEntityManager();
        
        try {
            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;
        } finally {
            em.close();
        }        
    }
    
    public User get(String email) throws Exception {
        EntityManager em = DBUtil.getEmfactory().createEntityManager();

        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }        
    }

    public void add(User user) throws Exception {
        EntityManager em = DBUtil.getEmfactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void update(User user) throws Exception {
        EntityManager em = DBUtil.getEmfactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void delete(User user) throws Exception {
        EntityManager em = DBUtil.getEmfactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.remove(em.merge(user));
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
