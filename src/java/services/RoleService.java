/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author joekim
 */
public class RoleService {

    public Role get(int role_id) throws Exception{
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(role_id);
        return role;
    }
}
