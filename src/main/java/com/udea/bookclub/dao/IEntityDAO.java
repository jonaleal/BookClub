/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.udea.bookclub.dao;

import java.util.List;

/**
 *
 * @author Jon Leal
 * @param <T>
 */
public interface IEntityDAO<T> {

    void create(T entity);

    void edit(T entity);

    void destroy(T entity);

    List<T> findEntities();

    T find(T entity);
}
