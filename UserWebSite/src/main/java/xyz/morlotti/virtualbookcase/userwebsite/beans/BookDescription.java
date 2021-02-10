package xyz.morlotti.virtualbookcase.userwebsite.beans;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDescription implements java.io.Serializable
{
     private Integer id;

     private String isbn;

     private String title;

     private String authorFirstname;

     private String authorLastname;

     private String editor;

     private Integer editionNumber;

     private Integer editionYear;

     private String genre;

     private String format;

     private String comment;

     private Set<Book> books;

     private Date created;
}
