/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.bookclub.business;

import com.udea.bookclub.dao.DiscussionDAO;
import com.udea.bookclub.dao.IEntityDAO;
import com.udea.bookclub.domain.Discussion;

/**
 *
 * @author Jon Leal
 */
public class DiscussionBusiness implements IDiscussionBusiness {

    private final IEntityDAO<Discussion> discussionDAO;

    public DiscussionBusiness() {
        this.discussionDAO = new DiscussionDAO();
    }
    
    @Override
    public void createDiscussion(Discussion discussion) {
        discussionDAO.create(discussion);
    }

    @Override
    public void deleteDiscussion(Integer discussionId) {
        discussionDAO.destroy(new Discussion(discussionId));
    }
    
}
