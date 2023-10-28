/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.bookclub.dao;

import com.udea.bookclub.dao.exceptions.IllegalOrphanException;
import com.udea.bookclub.dao.exceptions.NonexistentEntityException;
import com.udea.bookclub.domain.BookClub;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.udea.bookclub.domain.User;
import java.util.ArrayList;
import java.util.List;
import com.udea.bookclub.domain.Discussion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Jon Leal
 */
public class BookClubJpaController implements Serializable {

    public BookClubJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BookClub bookClub) {
        if (bookClub.getUserList() == null) {
            bookClub.setUserList(new ArrayList<User>());
        }
        if (bookClub.getDiscussionList() == null) {
            bookClub.setDiscussionList(new ArrayList<Discussion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User userName = bookClub.getUserName();
            if (userName != null) {
                userName = em.getReference(userName.getClass(), userName.getUserName());
                bookClub.setUserName(userName);
            }
            List<User> attachedUserList = new ArrayList<User>();
            for (User userListUserToAttach : bookClub.getUserList()) {
                userListUserToAttach = em.getReference(userListUserToAttach.getClass(), userListUserToAttach.getUserName());
                attachedUserList.add(userListUserToAttach);
            }
            bookClub.setUserList(attachedUserList);
            List<Discussion> attachedDiscussionList = new ArrayList<Discussion>();
            for (Discussion discussionListDiscussionToAttach : bookClub.getDiscussionList()) {
                discussionListDiscussionToAttach = em.getReference(discussionListDiscussionToAttach.getClass(), discussionListDiscussionToAttach.getDiscussionId());
                attachedDiscussionList.add(discussionListDiscussionToAttach);
            }
            bookClub.setDiscussionList(attachedDiscussionList);
            em.persist(bookClub);
            if (userName != null) {
                userName.getBookClubList().add(bookClub);
                userName = em.merge(userName);
            }
            for (User userListUser : bookClub.getUserList()) {
                userListUser.getBookClubList().add(bookClub);
                userListUser = em.merge(userListUser);
            }
            for (Discussion discussionListDiscussion : bookClub.getDiscussionList()) {
                BookClub oldClubIdOfDiscussionListDiscussion = discussionListDiscussion.getClubId();
                discussionListDiscussion.setClubId(bookClub);
                discussionListDiscussion = em.merge(discussionListDiscussion);
                if (oldClubIdOfDiscussionListDiscussion != null) {
                    oldClubIdOfDiscussionListDiscussion.getDiscussionList().remove(discussionListDiscussion);
                    oldClubIdOfDiscussionListDiscussion = em.merge(oldClubIdOfDiscussionListDiscussion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BookClub bookClub) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BookClub persistentBookClub = em.find(BookClub.class, bookClub.getClubId());
            User userNameOld = persistentBookClub.getUserName();
            User userNameNew = bookClub.getUserName();
            List<User> userListOld = persistentBookClub.getUserList();
            List<User> userListNew = bookClub.getUserList();
            List<Discussion> discussionListOld = persistentBookClub.getDiscussionList();
            List<Discussion> discussionListNew = bookClub.getDiscussionList();
            List<String> illegalOrphanMessages = null;
            for (Discussion discussionListOldDiscussion : discussionListOld) {
                if (!discussionListNew.contains(discussionListOldDiscussion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Discussion " + discussionListOldDiscussion + " since its clubId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (userNameNew != null) {
                userNameNew = em.getReference(userNameNew.getClass(), userNameNew.getUserName());
                bookClub.setUserName(userNameNew);
            }
            List<User> attachedUserListNew = new ArrayList<User>();
            for (User userListNewUserToAttach : userListNew) {
                userListNewUserToAttach = em.getReference(userListNewUserToAttach.getClass(), userListNewUserToAttach.getUserName());
                attachedUserListNew.add(userListNewUserToAttach);
            }
            userListNew = attachedUserListNew;
            bookClub.setUserList(userListNew);
            List<Discussion> attachedDiscussionListNew = new ArrayList<Discussion>();
            for (Discussion discussionListNewDiscussionToAttach : discussionListNew) {
                discussionListNewDiscussionToAttach = em.getReference(discussionListNewDiscussionToAttach.getClass(), discussionListNewDiscussionToAttach.getDiscussionId());
                attachedDiscussionListNew.add(discussionListNewDiscussionToAttach);
            }
            discussionListNew = attachedDiscussionListNew;
            bookClub.setDiscussionList(discussionListNew);
            bookClub = em.merge(bookClub);
            if (userNameOld != null && !userNameOld.equals(userNameNew)) {
                userNameOld.getBookClubList().remove(bookClub);
                userNameOld = em.merge(userNameOld);
            }
            if (userNameNew != null && !userNameNew.equals(userNameOld)) {
                userNameNew.getBookClubList().add(bookClub);
                userNameNew = em.merge(userNameNew);
            }
            for (User userListOldUser : userListOld) {
                if (!userListNew.contains(userListOldUser)) {
                    userListOldUser.getBookClubList().remove(bookClub);
                    userListOldUser = em.merge(userListOldUser);
                }
            }
            for (User userListNewUser : userListNew) {
                if (!userListOld.contains(userListNewUser)) {
                    userListNewUser.getBookClubList().add(bookClub);
                    userListNewUser = em.merge(userListNewUser);
                }
            }
            for (Discussion discussionListNewDiscussion : discussionListNew) {
                if (!discussionListOld.contains(discussionListNewDiscussion)) {
                    BookClub oldClubIdOfDiscussionListNewDiscussion = discussionListNewDiscussion.getClubId();
                    discussionListNewDiscussion.setClubId(bookClub);
                    discussionListNewDiscussion = em.merge(discussionListNewDiscussion);
                    if (oldClubIdOfDiscussionListNewDiscussion != null && !oldClubIdOfDiscussionListNewDiscussion.equals(bookClub)) {
                        oldClubIdOfDiscussionListNewDiscussion.getDiscussionList().remove(discussionListNewDiscussion);
                        oldClubIdOfDiscussionListNewDiscussion = em.merge(oldClubIdOfDiscussionListNewDiscussion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bookClub.getClubId();
                if (findBookClub(id) == null) {
                    throw new NonexistentEntityException("The bookClub with id " + id + " no longer exists.");
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
            BookClub bookClub;
            try {
                bookClub = em.getReference(BookClub.class, id);
                bookClub.getClubId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bookClub with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Discussion> discussionListOrphanCheck = bookClub.getDiscussionList();
            for (Discussion discussionListOrphanCheckDiscussion : discussionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BookClub (" + bookClub + ") cannot be destroyed since the Discussion " + discussionListOrphanCheckDiscussion + " in its discussionList field has a non-nullable clubId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            User userName = bookClub.getUserName();
            if (userName != null) {
                userName.getBookClubList().remove(bookClub);
                userName = em.merge(userName);
            }
            List<User> userList = bookClub.getUserList();
            for (User userListUser : userList) {
                userListUser.getBookClubList().remove(bookClub);
                userListUser = em.merge(userListUser);
            }
            em.remove(bookClub);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BookClub> findBookClubEntities() {
        return findBookClubEntities(true, -1, -1);
    }

    public List<BookClub> findBookClubEntities(int maxResults, int firstResult) {
        return findBookClubEntities(false, maxResults, firstResult);
    }

    private List<BookClub> findBookClubEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BookClub.class));
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

    public BookClub findBookClub(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BookClub.class, id);
        } finally {
            em.close();
        }
    }

    public int getBookClubCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BookClub> rt = cq.from(BookClub.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<BookClub> findByUserName(String userName) {
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("BookClub.findByUserName");
        query.setParameter("userName", userName);
        return query.getResultList();
    }

}
