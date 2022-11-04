/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.persistence.EntityManager;
import models.Role;
import models.User;

/**
 *
 * @author joekim
 */
public class RoleDB {
    public Role get(int role_id) throws Exception {
        EntityManager em = DBUtil.getEmfactory().createEntityManager();

        try {
            Role role = em.find(Role.class, role_id);
            return role;
        } finally {
            em.close();
        }   
    }
}
