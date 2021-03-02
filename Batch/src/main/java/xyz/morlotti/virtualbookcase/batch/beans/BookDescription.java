package xyz.morlotti.virtualbookcase.batch.beans;

import java.util.Date;
import java.util.List;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

     private Date created;

     ////////

     private List<Book> books;
}
