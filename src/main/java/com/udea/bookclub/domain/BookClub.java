/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.bookclub.domain;

import java.io.Serializable;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Jon Leal
 */
@Entity
@Table(name = "book_club")
@NamedQueries({
    @NamedQuery(name = "BookClub.findAll", query = "SELECT b FROM BookClub b"),
    @NamedQuery(name = "BookClub.findByClubId", query = "SELECT b FROM BookClub b WHERE b.clubId = :clubId"),
    @NamedQuery(name = "BookClub.findByName", query = "SELECT b FROM BookClub b WHERE b.name = :name"),
    @NamedQuery(name = "BookClub.findByMeetLink", query = "SELECT b FROM BookClub b WHERE b.meetLink = :meetLink"),
    @NamedQuery(name = "BookClub.findByUserName", query = "SELECT b FROM BookClub b WHERE b.userName.userName = :userName")})
public class BookClub implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "club_id")
    private Integer clubId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Lob
    @Size(max = 65535)
    @Column(name = "tags")
    private String tags;
    @Size(max = 255)
    @Column(name = "meet_link")
    private String meetLink;
    @ManyToMany(mappedBy = "bookClubList", fetch = FetchType.LAZY)
    private List<User> userList;
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clubId", fetch = FetchType.LAZY)
    private List<Discussion> discussionList;

    public BookClub() {
    }

    public BookClub(Integer clubId) {
        this.clubId = clubId;
    }

    public BookClub(Integer clubId, String name) {
        this.clubId = clubId;
        this.name = name;
    }

    public BookClub(String name, String descripcion, String tags, String meetLink, User userName) {
        this.name = name;
        this.description = descripcion;
        this.tags = tags;
        this.meetLink = meetLink;
        this.userName = userName;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getMeetLink() {
        return meetLink;
    }

    public void setMeetLink(String meetLink) {
        this.meetLink = meetLink;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUserName() {
        return userName;
    }

    public void setUserName(User userName) {
        this.userName = userName;
    }

    public List<Discussion> getDiscussionList() {
        return discussionList;
    }

    public void setDiscussionList(List<Discussion> discussionList) {
        this.discussionList = discussionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clubId != null ? clubId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookClub)) {
            return false;
        }
        BookClub other = (BookClub) object;
        if ((this.clubId == null && other.clubId != null) || (this.clubId != null && !this.clubId.equals(other.clubId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udea.bookclub.domain.BookClub[ clubId=" + clubId + " ]";
    }
    
}
