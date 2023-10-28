/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.udea.bookclub.business;

import com.udea.bookclub.domain.BookClub;
import java.util.List;

/**
 *
 * @author Jon Leal
 */
public interface IBookClubBusiness {

    void createBookClub(BookClub bookClub);

    List<BookClub> getBookClubs();

    BookClub getBookClub(BookClub bookClub);

    List<BookClub> getBookClubsByUser(String userName);
    
    void deleteBookClub(Integer clubId);
    
    void updateBookClub(BookClub bookClub);
}
