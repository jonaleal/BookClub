/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.udea.bookclub.dao;

import com.udea.bookclub.domain.BookClub;
import java.util.List;

/**
 *
 * @author Jon Leal
 */
public interface IBookClubDAO extends IEntityDAO<BookClub> {

    List<BookClub> getBookClubsByUser(String userName);
}
