<%-- 
    Document   : users
    Created on : Oct 20, 2022, 12:37:36 PM
    Author     : joekim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users List</title>
    </head>
    <body>
        <h1>Manage Users</h1>
            <c:if test="${users.size() eq 0}">
                <h3>No users found. Please add a user.</h3>
            </c:if>
            <c:if test="${users.size() ne 0}">  
                <table border="1">
                    <tr>
                      <th>Email</th>
                       <th>First Name</th>
                      <th>Last Name</th>
                       <th>Role</th>
                       <th></th>
                       <th></th>
                    </tr>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.email}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.role.role_name}</td>
                            <td><a href="<c:url value="user"><c:param name="action" value="edit" /><c:param name="email" value="${user.email}" /></c:url>">Edit</a></td>
                            <td><a href="<c:url value="user"><c:param name="action" value="delete" /><c:param name="email" value="${user.email}" /></c:url>">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        <c:if test="${selectedUser eq null}">
            <h2>Add User</h2><br>
            <form action="users" method="post">
                Email: <input type="email" name="email" value=""><br>
                First name: <input type="text" name="firstname" value=""><br>
                Last name: <input type="text" name="lastname" value=""><br>
                Password: <input type="password" name="password" value=""><br>
                Role: <select name="role">
                    <option value="1">system admin</option>
                    <option value="2">regular user</option>
                </select><br>
                <input type="hidden" name="action" value="create">
                <input type="submit" value="Add user">
            </form>
            ${error}
        </c:if>
        <c:if test="${selectedUser ne null}">
            <h2>Edit User</h2><br>
            <form action="users" method="post">
                Email ${selectedUser.email}<br>
                First name: <input type="text" name="firstname" value="${selectedUser.firstName}"><br>
                Last name: <input type="text" name="lastname" value="${selectedUser.lastName}"><br>
                Password: <input type="password" name="password" value=""><br>
                Role: <select name="role" value="role">
                    <option value="1" <c:if test="${selectedUser.role.role_id eq 1}">selected</c:if> >system admin</option>
                    <option value="2" <c:if test="${selectedUser.role.role_id eq 2}">selected</c:if> >regular user</option>
                </select><br>
                <input type="hidden" name="action" value="update">
                <input type="submit" value="Update">
            </form>
            <form action="users" method="post">
                <input type="hidden" name="action" value="cancel">
                <input type="submit" value="Cancel">
            </form>
            ${error}
        </c:if>
    </body>
</html>
