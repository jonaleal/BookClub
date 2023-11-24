package com.udea.bookclub.domain;

import com.udea.bookclub.domain.BookClub;
import com.udea.bookclub.domain.Comment;
import com.udea.bookclub.domain.Discussion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-11-24T13:35:25")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile ListAttribute<User, BookClub> bookClubList;
    public static volatile ListAttribute<User, Comment> commentList;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Date> birthdate;
    public static volatile ListAttribute<User, BookClub> bookClubList1;
    public static volatile SingularAttribute<User, String> pictureUrl;
    public static volatile ListAttribute<User, Discussion> discussionList;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile SingularAttribute<User, String> email;

}