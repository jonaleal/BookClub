package com.udea.bookclub.domain;

import com.udea.bookclub.domain.BookClub;
import com.udea.bookclub.domain.Comment;
import com.udea.bookclub.domain.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-11-26T16:59:28")
@StaticMetamodel(Discussion.class)
public class Discussion_ { 

    public static volatile ListAttribute<Discussion, Comment> commentList;
    public static volatile SingularAttribute<Discussion, Date> createdAt;
    public static volatile SingularAttribute<Discussion, Integer> discussionId;
    public static volatile SingularAttribute<Discussion, String> description;
    public static volatile SingularAttribute<Discussion, BookClub> clubId;
    public static volatile SingularAttribute<Discussion, User> userName;
    public static volatile SingularAttribute<Discussion, String> tittle;

}