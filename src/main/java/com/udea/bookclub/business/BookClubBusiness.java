/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.bookclub.business;

import com.udea.bookclub.dao.BookClubDAO;
import com.udea.bookclub.dao.IEntityDAO;
import com.udea.bookclub.domain.BookClub;
import java.util.List;

/**
 *
 * @author Jon Leal
 */
public class BookClubBusiness implements IBookClubBusiness {

    private final IEntityDAO<BookClub> bookClubDAO;

    public BookClubBusiness() {
        this.bookClubDAO = new BookClubDAO();
    }

    @Override
    public void createBookClub(BookClub bookClub) {
        bookClubDAO.create(bookClub);
    }

    @Override
    public List<BookClub> getBookClubs() {
        return bookClubDAO.findEntities();
    }

    @Override
    public BookClub getBookClub(BookClub bookClub) {
        return bookClubDAO.find(bookClub);
    }

}
