/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.bookclub.dao;

import com.udea.bookclub.domain.Discussion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;

/**
 *
 * @author Jon Leal
 */
public class DiscussionDAO implements IEntityDAO<Discussion> {

    DiscussionJpaController discussionController;

    public DiscussionDAO() {
        this.discussionController = new DiscussionJpaController(Persistence.createEntityManagerFactory("com.udea_BookClub"));
    }
    
    @Override
    public void create(Discussion entity) {
        try {
            discussionController.create(entity);
        } catch (Exception ex) {
            Logger.getLogger(DiscussionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void edit(Discussion entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void destroy(Discussion entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Discussion> findEntities() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Discussion find(Discussion entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
