/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.bookclub.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Jon Leal
 */
@Entity
@Table(name = "discussion")
@NamedQueries({
    @NamedQuery(name = "Discussion.findAll", query = "SELECT d FROM Discussion d"),
    @NamedQuery(name = "Discussion.findByDiscussionId", query = "SELECT d FROM Discussion d WHERE d.discussionId = :discussionId"),
    @NamedQuery(name = "Discussion.findByTittle", query = "SELECT d FROM Discussion d WHERE d.tittle = :tittle"),
    @NamedQuery(name = "Discussion.findByCreatedAt", query = "SELECT d FROM Discussion d WHERE d.createdAt = :createdAt")})
public class Discussion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "discussion_id")
    private Integer discussionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tittle")
    private String tittle;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discussionId", fetch = FetchType.LAZY)
    private List<Comment> commentList;
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userName;
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BookClub clubId;

    public Discussion() {
    }

    public Discussion(Integer discussionId) {
        this.discussionId = discussionId;
    }

    public Discussion(String tittle, String description, Date createdAt, User userName, BookClub clubId) {
        this.tittle = tittle;
        this.description = description;
        this.createdAt = createdAt;
        this.userName = userName;
        this.clubId = clubId;
    }
    

    public Discussion(Integer discussionId, String tittle, String description, Date createdAt) {
        this.discussionId = discussionId;
        this.tittle = tittle;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Integer getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(Integer discussionId) {
        this.discussionId = discussionId;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public User getUserName() {
        return userName;
    }

    public void setUserName(User userName) {
        this.userName = userName;
    }

    public BookClub getClubId() {
        return clubId;
    }

    public void setClubId(BookClub clubId) {
        this.clubId = clubId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (discussionId != null ? discussionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Discussion)) {
            return false;
        }
        Discussion other = (Discussion) object;
        if ((this.discussionId == null && other.discussionId != null) || (this.discussionId != null && !this.discussionId.equals(other.discussionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udea.bookclub.domain.Discussion[ discussionId=" + discussionId + " ]";
    }
    
}
