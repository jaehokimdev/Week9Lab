/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

/**
 *
 * @author joekim
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       UserService us = new UserService();
       String action = request.getParameter("action");
     
       try {
            List<User> users = us.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
       
       if (action != null && action.equals("edit")) {
           try {
               HttpSession session = request.getSession();
               String email = request.getParameter("email");
               session.setAttribute("selEmail", email);
               User user = us.get(email);
               request.setAttribute("selectedUser", user);
           } catch (Exception ex) {
               Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       
       if (action != null && action.equals("delete")) {
           try {
               String email = request.getParameter("email");
               us.delete(email);
           } catch (Exception ex) {
               Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       
       try {
            List<User> users = us.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
        
       getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserService us = new UserService();
        RoleService rs = new RoleService();

        String action = request.getParameter("action");
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");
        String selectedrole = request.getParameter("role");
        
        Role role = new Role();
        try {
            role = rs.get(Integer.parseInt(selectedrole));
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            switch (action) {
                case "create":
                    if (email.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || password.isEmpty()) {
                        request.setAttribute("error", "All fields are required");
                        break;
                    }
                    us.add(email, firstname, lastname, password, role);
                    break;
                case "update":
                    HttpSession session = request.getSession();
                    String selEmail = (String) session.getAttribute("selEmail");
                    if (firstname.isEmpty() || lastname.isEmpty() || password.isEmpty()) {
                        request.setAttribute("error", "All fields are required");
                        User user = us.get(selEmail);
                        request.setAttribute("selectedUser", user);
                        break;
                    }
                    us.update(selEmail, firstname, lastname, password, role);
                    break;
                case "cancel":
                    request.setAttribute("selectedUser", null);
                    break;
            }
            request.setAttribute("message", action);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
        
        try {
            List<User> users = us.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);

    }

}
