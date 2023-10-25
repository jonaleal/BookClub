/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.bookclub.dao;

import com.udea.bookclub.dao.exceptions.IllegalOrphanException;
import com.udea.bookclub.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.udea.bookclub.domain.User;
import com.udea.bookclub.domain.BookClub;
import com.udea.bookclub.domain.Comment;
import com.udea.bookclub.domain.Discussion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Jon Leal
 */
public class DiscussionJpaController implements Serializable {

    public DiscussionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Discussion discussion) {
        if (discussion.getCommentList() == null) {
            discussion.setCommentList(new ArrayList<Comment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User userName = discussion.getUserName();
            if (userName != null) {
                userName = em.getReference(userName.getClass(), userName.getUserName());
                discussion.setUserName(userName);
            }
            BookClub clubId = discussion.getClubId();
            if (clubId != null) {
                clubId = em.getReference(clubId.getClass(), clubId.getClubId());
                discussion.setClubId(clubId);
            }
            List<Comment> attachedCommentList = new ArrayList<Comment>();
            for (Comment commentListCommentToAttach : discussion.getCommentList()) {
                commentListCommentToAttach = em.getReference(commentListCommentToAttach.getClass(), commentListCommentToAttach.getCommentId());
                attachedCommentList.add(commentListCommentToAttach);
            }
            discussion.setCommentList(attachedCommentList);
            em.persist(discussion);
            if (userName != null) {
                userName.getDiscussionList().add(discussion);
                userName = em.merge(userName);
            }
            if (clubId != null) {
                clubId.getDiscussionList().add(discussion);
                clubId = em.merge(clubId);
            }
            for (Comment commentListComment : discussion.getCommentList()) {
                Discussion oldDiscussionIdOfCommentListComment = commentListComment.getDiscussionId();
                commentListComment.setDiscussionId(discussion);
                commentListComment = em.merge(commentListComment);
                if (oldDiscussionIdOfCommentListComment != null) {
                    oldDiscussionIdOfCommentListComment.getCommentList().remove(commentListComment);
                    oldDiscussionIdOfCommentListComment = em.merge(oldDiscussionIdOfCommentListComment);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Discussion discussion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Discussion persistentDiscussion = em.find(Discussion.class, discussion.getDiscussionId());
            User userNameOld = persistentDiscussion.getUserName();
            User userNameNew = discussion.getUserName();
            BookClub clubIdOld = persistentDiscussion.getClubId();
            BookClub clubIdNew = discussion.getClubId();
            List<Comment> commentListOld = persistentDiscussion.getCommentList();
            List<Comment> commentListNew = discussion.getCommentList();
            List<String> illegalOrphanMessages = null;
            for (Comment commentListOldComment : commentListOld) {
                if (!commentListNew.contains(commentListOldComment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comment " + commentListOldComment + " since its discussionId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (userNameNew != null) {
                userNameNew = em.getReference(userNameNew.getClass(), userNameNew.getUserName());
                discussion.setUserName(userNameNew);
            }
            if (clubIdNew != null) {
                clubIdNew = em.getReference(clubIdNew.getClass(), clubIdNew.getClubId());
                discussion.setClubId(clubIdNew);
            }
            List<Comment> attachedCommentListNew = new ArrayList<Comment>();
            for (Comment commentListNewCommentToAttach : commentListNew) {
                commentListNewCommentToAttach = em.getReference(commentListNewCommentToAttach.getClass(), commentListNewCommentToAttach.getCommentId());
                attachedCommentListNew.add(commentListNewCommentToAttach);
            }
            commentListNew = attachedCommentListNew;
            discussion.setCommentList(commentListNew);
            discussion = em.merge(discussion);
            if (userNameOld != null && !userNameOld.equals(userNameNew)) {
                userNameOld.getDiscussionList().remove(discussion);
                userNameOld = em.merge(userNameOld);
            }
            if (userNameNew != null && !userNameNew.equals(userNameOld)) {
                userNameNew.getDiscussionList().add(discussion);
                userNameNew = em.merge(userNameNew);
            }
            if (clubIdOld != null && !clubIdOld.equals(clubIdNew)) {
                clubIdOld.getDiscussionList().remove(discussion);
                clubIdOld = em.merge(clubIdOld);
            }
            if (clubIdNew != null && !clubIdNew.equals(clubIdOld)) {
                clubIdNew.getDiscussionList().add(discussion);
                clubIdNew = em.merge(clubIdNew);
            }
            for (Comment commentListNewComment : commentListNew) {
                if (!commentListOld.contains(commentListNewComment)) {
                    Discussion oldDiscussionIdOfCommentListNewComment = commentListNewComment.getDiscussionId();
                    commentListNewComment.setDiscussionId(discussion);
                    commentListNewComment = em.merge(commentListNewComment);
                    if (oldDiscussionIdOfCommentListNewComment != null && !oldDiscussionIdOfCommentListNewComment.equals(discussion)) {
                        oldDiscussionIdOfCommentListNewComment.getCommentList().remove(commentListNewComment);
                        oldDiscussionIdOfCommentListNewComment = em.merge(oldDiscussionIdOfCommentListNewComment);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = discussion.getDiscussionId();
                if (findDiscussion(id) == null) {
                    throw new NonexistentEntityException("The discussion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Discussion discussion;
            try {
                discussion = em.getReference(Discussion.class, id);
                discussion.getDiscussionId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The discussion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Comment> commentListOrphanCheck = discussion.getCommentList();
            for (Comment commentListOrphanCheckComment : commentListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Discussion (" + discussion + ") cannot be destroyed since the Comment " + commentListOrphanCheckComment + " in its commentList field has a non-nullable discussionId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            User userName = discussion.getUserName();
            if (userName != null) {
                userName.getDiscussionList().remove(discussion);
                userName = em.merge(userName);
            }
            BookClub clubId = discussion.getClubId();
            if (clubId != null) {
                clubId.getDiscussionList().remove(discussion);
                clubId = em.merge(clubId);
            }
            em.remove(discussion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Discussion> findDiscussionEntities() {
        return findDiscussionEntities(true, -1, -1);
    }

    public List<Discussion> findDiscussionEntities(int maxResults, int firstResult) {
        return findDiscussionEntities(false, maxResults, firstResult);
    }

    private List<Discussion> findDiscussionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Discussion.class));
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

    public Discussion findDiscussion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Discussion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiscussionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Discussion> rt = cq.from(Discussion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
