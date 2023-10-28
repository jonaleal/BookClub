package com.udea.bookclub.domain;

import com.udea.bookclub.domain.Discussion;
import com.udea.bookclub.domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-28T15:07:01")
@StaticMetamodel(BookClub.class)
public class BookClub_ { 

    public static volatile ListAttribute<BookClub, User> userList;
    public static volatile SingularAttribute<BookClub, String> meetLink;
    public static volatile SingularAttribute<BookClub, String> name;
    public static volatile SingularAttribute<BookClub, Integer> clubId;
    public static volatile SingularAttribute<BookClub, String> description;
    public static volatile ListAttribute<BookClub, Discussion> discussionList;
    public static volatile SingularAttribute<BookClub, User> userName;
    public static volatile SingularAttribute<BookClub, String> tags;

}