/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.udea.bookclub.business;

import com.udea.bookclub.domain.Discussion;

/**
 *
 * @author Jon Leal
 */
public interface IDiscussionBusiness {

    void createDiscussion(Discussion discussion);
    
    void deleteDiscussion(Integer discussionId);
}
