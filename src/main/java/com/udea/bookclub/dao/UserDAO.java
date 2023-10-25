/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.bookclub.dao;

import com.udea.bookclub.dao.exceptions.IllegalOrphanException;
import com.udea.bookclub.dao.exceptions.NonexistentEntityException;
import com.udea.bookclub.domain.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;

/**
 *
 * @author Jon Leal
 */
public class UserDAO implements IUserDAO {

    UserJpaController userController;

    public UserDAO() {
        this.userController = new UserJpaController(Persistence.createEntityManagerFactory("com.udea_BookClub"));
    }

    @Override
    public void create(User user) {
        try {
            userController.create(user);
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void edit(User user) {
        try {
            userController.edit(user);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void destroy(User user) {
        try {
            userController.destroy(user.getUserName());
        } catch (NonexistentEntityException | IllegalOrphanException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> findEntities() {
        return userController.findUserEntities();
    }

    @Override
    public User find(User user) {
        return userController.findUser(user.getUserName());
    }

    @Override
    public boolean verifyCredentials(User user) {
        return userController.verifyCredentials(user);
    }

}
