/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.bookclub.business;

import com.udea.bookclub.dao.IUserDAO;
import com.udea.bookclub.dao.UserDAO;
import com.udea.bookclub.domain.User;
import java.util.List;

/**
 *
 * @author Jon Leal
 */
public class UserBusiness implements IUserBusiness {

    private final IUserDAO userDAO;

    public UserBusiness() {
        this.userDAO = new UserDAO();
    }

    @Override
    public void createUser(User user) {
        userDAO.create(user);
    }

    @Override
    public boolean verifyCredentials(User user) {
        return userDAO.verifyCredentials(user);
    }

    @Override
    public List<User> getUsers() {
        return userDAO.findEntities();
    }

    @Override
    public User getUser(User user) {
        return userDAO.find(user);
    }

    @Override
    public boolean isRegisteredUser(User user) {
        return userDAO.find(user) != null;
    }

}
