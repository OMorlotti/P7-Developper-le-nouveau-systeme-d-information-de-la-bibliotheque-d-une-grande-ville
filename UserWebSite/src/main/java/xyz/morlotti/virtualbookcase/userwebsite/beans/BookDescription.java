package xyz.morlotti.virtualbookcase.userwebsite.beans;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class BookDescription implements java.io.Serializable {

     private Integer id;
     private String isbn;
     private String title;
     private String authorFirstname;
     private String authorLastname;
     private String editor;
     private Integer edition;
     private Integer editionYear;
     private String format;
     private String comment;
     private Date created;
     private Date modified;
     private Set<BookGenre> bookGenres = new HashSet<>(0);
     private Set<Book> books = new HashSet<>(0);
}


