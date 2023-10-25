/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.bookclub.dao;

import com.udea.bookclub.dao.exceptions.IllegalOrphanException;
import com.udea.bookclub.dao.exceptions.NonexistentEntityException;
import com.udea.bookclub.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.udea.bookclub.domain.BookClub;
import java.util.ArrayList;
import java.util.List;
import com.udea.bookclub.domain.Comment;
import com.udea.bookclub.domain.Discussion;
import com.udea.bookclub.domain.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jon Leal
 */
public class UserJpaController implements Serializable {

    public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) throws PreexistingEntityException, Exception {
        if (user.getBookClubList() == null) {
            user.setBookClubList(new ArrayList<BookClub>());
        }
        if (user.getBookClubList1() == null) {
            user.setBookClubList1(new ArrayList<BookClub>());
        }
        if (user.getCommentList() == null) {
            user.setCommentList(new ArrayList<Comment>());
        }
        if (user.getDiscussionList() == null) {
            user.setDiscussionList(new ArrayList<Discussion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<BookClub> attachedBookClubList = new ArrayList<BookClub>();
            for (BookClub bookClubListBookClubToAttach : user.getBookClubList()) {
                bookClubListBookClubToAttach = em.getReference(bookClubListBookClubToAttach.getClass(), bookClubListBookClubToAttach.getClubId());
                attachedBookClubList.add(bookClubListBookClubToAttach);
            }
            user.setBookClubList(attachedBookClubList);
            List<BookClub> attachedBookClubList1 = new ArrayList<BookClub>();
            for (BookClub bookClubList1BookClubToAttach : user.getBookClubList1()) {
                bookClubList1BookClubToAttach = em.getReference(bookClubList1BookClubToAttach.getClass(), bookClubList1BookClubToAttach.getClubId());
                attachedBookClubList1.add(bookClubList1BookClubToAttach);
            }
            user.setBookClubList1(attachedBookClubList1);
            List<Comment> attachedCommentList = new ArrayList<Comment>();
            for (Comment commentListCommentToAttach : user.getCommentList()) {
                commentListCommentToAttach = em.getReference(commentListCommentToAttach.getClass(), commentListCommentToAttach.getCommentId());
                attachedCommentList.add(commentListCommentToAttach);
            }
            user.setCommentList(attachedCommentList);
            List<Discussion> attachedDiscussionList = new ArrayList<Discussion>();
            for (Discussion discussionListDiscussionToAttach : user.getDiscussionList()) {
                discussionListDiscussionToAttach = em.getReference(discussionListDiscussionToAttach.getClass(), discussionListDiscussionToAttach.getDiscussionId());
                attachedDiscussionList.add(discussionListDiscussionToAttach);
            }
            user.setDiscussionList(attachedDiscussionList);
            em.persist(user);
            for (BookClub bookClubListBookClub : user.getBookClubList()) {
                bookClubListBookClub.getUserList().add(user);
                bookClubListBookClub = em.merge(bookClubListBookClub);
            }
            for (BookClub bookClubList1BookClub : user.getBookClubList1()) {
                User oldUserNameOfBookClubList1BookClub = bookClubList1BookClub.getUserName();
                bookClubList1BookClub.setUserName(user);
                bookClubList1BookClub = em.merge(bookClubList1BookClub);
                if (oldUserNameOfBookClubList1BookClub != null) {
                    oldUserNameOfBookClubList1BookClub.getBookClubList1().remove(bookClubList1BookClub);
                    oldUserNameOfBookClubList1BookClub = em.merge(oldUserNameOfBookClubList1BookClub);
                }
            }
            for (Comment commentListComment : user.getCommentList()) {
                User oldUserNameOfCommentListComment = commentListComment.getUserName();
                commentListComment.setUserName(user);
                commentListComment = em.merge(commentListComment);
                if (oldUserNameOfCommentListComment != null) {
                    oldUserNameOfCommentListComment.getCommentList().remove(commentListComment);
                    oldUserNameOfCommentListComment = em.merge(oldUserNameOfCommentListComment);
                }
            }
            for (Discussion discussionListDiscussion : user.getDiscussionList()) {
                User oldUserNameOfDiscussionListDiscussion = discussionListDiscussion.getUserName();
                discussionListDiscussion.setUserName(user);
                discussionListDiscussion = em.merge(discussionListDiscussion);
                if (oldUserNameOfDiscussionListDiscussion != null) {
                    oldUserNameOfDiscussionListDiscussion.getDiscussionList().remove(discussionListDiscussion);
                    oldUserNameOfDiscussionListDiscussion = em.merge(oldUserNameOfDiscussionListDiscussion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUser(user.getUserName()) != null) {
                throw new PreexistingEntityException("User " + user + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getUserName());
            List<BookClub> bookClubListOld = persistentUser.getBookClubList();
            List<BookClub> bookClubListNew = user.getBookClubList();
            List<BookClub> bookClubList1Old = persistentUser.getBookClubList1();
            List<BookClub> bookClubList1New = user.getBookClubList1();
            List<Comment> commentListOld = persistentUser.getCommentList();
            List<Comment> commentListNew = user.getCommentList();
            List<Discussion> discussionListOld = persistentUser.getDiscussionList();
            List<Discussion> discussionListNew = user.getDiscussionList();
            List<String> illegalOrphanMessages = null;
            for (BookClub bookClubList1OldBookClub : bookClubList1Old) {
                if (!bookClubList1New.contains(bookClubList1OldBookClub)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BookClub " + bookClubList1OldBookClub + " since its userName field is not nullable.");
                }
            }
            for (Comment commentListOldComment : commentListOld) {
                if (!commentListNew.contains(commentListOldComment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comment " + commentListOldComment + " since its userName field is not nullable.");
                }
            }
            for (Discussion discussionListOldDiscussion : discussionListOld) {
                if (!discussionListNew.contains(discussionListOldDiscussion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Discussion " + discussionListOldDiscussion + " since its userName field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<BookClub> attachedBookClubListNew = new ArrayList<BookClub>();
            for (BookClub bookClubListNewBookClubToAttach : bookClubListNew) {
                bookClubListNewBookClubToAttach = em.getReference(bookClubListNewBookClubToAttach.getClass(), bookClubListNewBookClubToAttach.getClubId());
                attachedBookClubListNew.add(bookClubListNewBookClubToAttach);
            }
            bookClubListNew = attachedBookClubListNew;
            user.setBookClubList(bookClubListNew);
            List<BookClub> attachedBookClubList1New = new ArrayList<BookClub>();
            for (BookClub bookClubList1NewBookClubToAttach : bookClubList1New) {
                bookClubList1NewBookClubToAttach = em.getReference(bookClubList1NewBookClubToAttach.getClass(), bookClubList1NewBookClubToAttach.getClubId());
                attachedBookClubList1New.add(bookClubList1NewBookClubToAttach);
            }
            bookClubList1New = attachedBookClubList1New;
            user.setBookClubList1(bookClubList1New);
            List<Comment> attachedCommentListNew = new ArrayList<Comment>();
            for (Comment commentListNewCommentToAttach : commentListNew) {
                commentListNewCommentToAttach = em.getReference(commentListNewCommentToAttach.getClass(), commentListNewCommentToAttach.getCommentId());
                attachedCommentListNew.add(commentListNewCommentToAttach);
            }
            commentListNew = attachedCommentListNew;
            user.setCommentList(commentListNew);
            List<Discussion> attachedDiscussionListNew = new ArrayList<Discussion>();
            for (Discussion discussionListNewDiscussionToAttach : discussionListNew) {
                discussionListNewDiscussionToAttach = em.getReference(discussionListNewDiscussionToAttach.getClass(), discussionListNewDiscussionToAttach.getDiscussionId());
                attachedDiscussionListNew.add(discussionListNewDiscussionToAttach);
            }
            discussionListNew = attachedDiscussionListNew;
            user.setDiscussionList(discussionListNew);
            user = em.merge(user);
            for (BookClub bookClubListOldBookClub : bookClubListOld) {
                if (!bookClubListNew.contains(bookClubListOldBookClub)) {
                    bookClubListOldBookClub.getUserList().remove(user);
                    bookClubListOldBookClub = em.merge(bookClubListOldBookClub);
                }
            }
            for (BookClub bookClubListNewBookClub : bookClubListNew) {
                if (!bookClubListOld.contains(bookClubListNewBookClub)) {
                    bookClubListNewBookClub.getUserList().add(user);
                    bookClubListNewBookClub = em.merge(bookClubListNewBookClub);
                }
            }
            for (BookClub bookClubList1NewBookClub : bookClubList1New) {
                if (!bookClubList1Old.contains(bookClubList1NewBookClub)) {
                    User oldUserNameOfBookClubList1NewBookClub = bookClubList1NewBookClub.getUserName();
                    bookClubList1NewBookClub.setUserName(user);
                    bookClubList1NewBookClub = em.merge(bookClubList1NewBookClub);
                    if (oldUserNameOfBookClubList1NewBookClub != null && !oldUserNameOfBookClubList1NewBookClub.equals(user)) {
                        oldUserNameOfBookClubList1NewBookClub.getBookClubList1().remove(bookClubList1NewBookClub);
                        oldUserNameOfBookClubList1NewBookClub = em.merge(oldUserNameOfBookClubList1NewBookClub);
                    }
                }
            }
            for (Comment commentListNewComment : commentListNew) {
                if (!commentListOld.contains(commentListNewComment)) {
                    User oldUserNameOfCommentListNewComment = commentListNewComment.getUserName();
                    commentListNewComment.setUserName(user);
                    commentListNewComment = em.merge(commentListNewComment);
                    if (oldUserNameOfCommentListNewComment != null && !oldUserNameOfCommentListNewComment.equals(user)) {
                        oldUserNameOfCommentListNewComment.getCommentList().remove(commentListNewComment);
                        oldUserNameOfCommentListNewComment = em.merge(oldUserNameOfCommentListNewComment);
                    }
                }
            }
            for (Discussion discussionListNewDiscussion : discussionListNew) {
                if (!discussionListOld.contains(discussionListNewDiscussion)) {
                    User oldUserNameOfDiscussionListNewDiscussion = discussionListNewDiscussion.getUserName();
                    discussionListNewDiscussion.setUserName(user);
                    discussionListNewDiscussion = em.merge(discussionListNewDiscussion);
                    if (oldUserNameOfDiscussionListNewDiscussion != null && !oldUserNameOfDiscussionListNewDiscussion.equals(user)) {
                        oldUserNameOfDiscussionListNewDiscussion.getDiscussionList().remove(discussionListNewDiscussion);
                        oldUserNameOfDiscussionListNewDiscussion = em.merge(oldUserNameOfDiscussionListNewDiscussion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = user.getUserName();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getUserName();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<BookClub> bookClubList1OrphanCheck = user.getBookClubList1();
            for (BookClub bookClubList1OrphanCheckBookClub : bookClubList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the BookClub " + bookClubList1OrphanCheckBookClub + " in its bookClubList1 field has a non-nullable userName field.");
            }
            List<Comment> commentListOrphanCheck = user.getCommentList();
            for (Comment commentListOrphanCheckComment : commentListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Comment " + commentListOrphanCheckComment + " in its commentList field has a non-nullable userName field.");
            }
            List<Discussion> discussionListOrphanCheck = user.getDiscussionList();
            for (Discussion discussionListOrphanCheckDiscussion : discussionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Discussion " + discussionListOrphanCheckDiscussion + " in its discussionList field has a non-nullable userName field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<BookClub> bookClubList = user.getBookClubList();
            for (BookClub bookClubListBookClub : bookClubList) {
                bookClubListBookClub.getUserList().remove(user);
                bookClubListBookClub = em.merge(bookClubListBookClub);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public boolean verifyCredentials(User user) {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<User> query = em.createNamedQuery("User.verifyCredentials", User.class)
                    .setParameter("userName", user.getUserName())
                    .setParameter("password", user.getPassword());

            User resultUser = query.getSingleResult();

            // If no exception is thrown, the credentials are valid
            return resultUser != null;
        } catch (NoResultException e) {
            // No user found with the provided credentials
            return false;
        } finally {
            em.close();
        }
    }

}
