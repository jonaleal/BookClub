/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.bookclub.dao;

import com.udea.bookclub.dao.exceptions.NonexistentEntityException;
import com.udea.bookclub.dao.exceptions.PreexistingEntityException;
import com.udea.bookclub.domain.Comment;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.udea.bookclub.domain.User;
import com.udea.bookclub.domain.Discussion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Jon Leal
 */
public class CommentJpaController implements Serializable {

    public CommentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comment comment) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User userName = comment.getUserName();
            if (userName != null) {
                userName = em.getReference(userName.getClass(), userName.getUserName());
                comment.setUserName(userName);
            }
            Discussion discussionId = comment.getDiscussionId();
            if (discussionId != null) {
                discussionId = em.getReference(discussionId.getClass(), discussionId.getDiscussionId());
                comment.setDiscussionId(discussionId);
            }
            em.persist(comment);
            if (userName != null) {
                userName.getCommentList().add(comment);
                userName = em.merge(userName);
            }
            if (discussionId != null) {
                discussionId.getCommentList().add(comment);
                discussionId = em.merge(discussionId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findComment(comment.getCommentId()) != null) {
                throw new PreexistingEntityException("Comment " + comment + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comment comment) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comment persistentComment = em.find(Comment.class, comment.getCommentId());
            User userNameOld = persistentComment.getUserName();
            User userNameNew = comment.getUserName();
            Discussion discussionIdOld = persistentComment.getDiscussionId();
            Discussion discussionIdNew = comment.getDiscussionId();
            if (userNameNew != null) {
                userNameNew = em.getReference(userNameNew.getClass(), userNameNew.getUserName());
                comment.setUserName(userNameNew);
            }
            if (discussionIdNew != null) {
                discussionIdNew = em.getReference(discussionIdNew.getClass(), discussionIdNew.getDiscussionId());
                comment.setDiscussionId(discussionIdNew);
            }
            comment = em.merge(comment);
            if (userNameOld != null && !userNameOld.equals(userNameNew)) {
                userNameOld.getCommentList().remove(comment);
                userNameOld = em.merge(userNameOld);
            }
            if (userNameNew != null && !userNameNew.equals(userNameOld)) {
                userNameNew.getCommentList().add(comment);
                userNameNew = em.merge(userNameNew);
            }
            if (discussionIdOld != null && !discussionIdOld.equals(discussionIdNew)) {
                discussionIdOld.getCommentList().remove(comment);
                discussionIdOld = em.merge(discussionIdOld);
            }
            if (discussionIdNew != null && !discussionIdNew.equals(discussionIdOld)) {
                discussionIdNew.getCommentList().add(comment);
                discussionIdNew = em.merge(discussionIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comment.getCommentId();
                if (findComment(id) == null) {
                    throw new NonexistentEntityException("The comment with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comment comment;
            try {
                comment = em.getReference(Comment.class, id);
                comment.getCommentId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comment with id " + id + " no longer exists.", enfe);
            }
            User userName = comment.getUserName();
            if (userName != null) {
                userName.getCommentList().remove(comment);
                userName = em.merge(userName);
            }
            Discussion discussionId = comment.getDiscussionId();
            if (discussionId != null) {
                discussionId.getCommentList().remove(comment);
                discussionId = em.merge(discussionId);
            }
            em.remove(comment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comment> findCommentEntities() {
        return findCommentEntities(true, -1, -1);
    }

    public List<Comment> findCommentEntities(int maxResults, int firstResult) {
        return findCommentEntities(false, maxResults, firstResult);
    }

    private List<Comment> findCommentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comment.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Comment findComment(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comment.class, id);
        } finally {
            em.close();
        }
    }

    public int getCommentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comment> rt = cq.from(Comment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
