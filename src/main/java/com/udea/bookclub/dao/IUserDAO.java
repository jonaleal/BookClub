/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.udea.bookclub.dao;

import com.udea.bookclub.domain.User;

/**
 *
 * @author Jon Leal
 */
public interface IUserDAO extends IEntityDAO<User>{

    boolean verifyCredentials(User user);
}
