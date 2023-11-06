/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.bookclub.business;

import com.udea.bookclub.dao.BookClubDAO;
import com.udea.bookclub.dao.IBookClubDAO;
import com.udea.bookclub.domain.BookClub;
import java.util.List;

/**
 *
 * @author Jon Leal
 */
public class BookClubBusiness implements IBookClubBusiness {

    private final IBookClubDAO bookClubDAO;

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

    @Override
    public List<BookClub> getCreatedBookClubsByUser(String userName) {
        return bookClubDAO.getCreatedBookClubsByUser(userName);
    }

    @Override
    public void deleteBookClub(Integer clubId) {
        bookClubDAO.destroy(new BookClub(clubId));
    }

    @Override
    public void updateBookClub(BookClub bookClub) {
        bookClubDAO.edit(bookClub);
    }

    @Override
    public List<BookClub> getJoinedBookClubsByUser(String userName) {
        return bookClubDAO.getJoinedBookClubsByUser(userName);
    }

}
