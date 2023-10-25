/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.udea.bookclub.business;

import com.udea.bookclub.domain.User;
import java.util.List;

/**
 *
 * @author Jon Leal
 */
public interface IUserBusiness {

    void createUser(User user);

    boolean verifyCredentials(User user);

    List<User> getUsers();
    
    User getUser(User user);
    
    boolean isRegisteredUser(User user);
}
