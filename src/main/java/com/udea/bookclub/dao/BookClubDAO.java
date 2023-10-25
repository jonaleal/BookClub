/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.bookclub.dao;

import com.udea.bookclub.dao.exceptions.IllegalOrphanException;
import com.udea.bookclub.dao.exceptions.NonexistentEntityException;
import com.udea.bookclub.domain.BookClub;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;

/**
 *
 * @author Jon Leal
 */
public class BookClubDAO implements IEntityDAO<BookClub> {
    
    BookClubJpaController bookClubController;

    public BookClubDAO() {
        this.bookClubController = new BookClubJpaController(Persistence.createEntityManagerFactory("com.udea_BookClub"));
    }

    @Override
    public void create(BookClub entity) {
        try {
            bookClubController.create(entity);
        } catch (Exception ex) {
            Logger.getLogger(BookClubDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void edit(BookClub entity) {
        try {
            bookClubController.edit(entity);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BookClubDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BookClubDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void destroy(BookClub entity) {
        try {
            bookClubController.destroy(entity.getClubId());
        } catch (IllegalOrphanException | NonexistentEntityException ex) {
            Logger.getLogger(BookClubDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<BookClub> findEntities() {
        return bookClubController.findBookClubEntities();
    }

    @Override
    public BookClub find(BookClub entity) {
        return bookClubController.findBookClub(entity.getClubId());
    }
    
}
