package xyz.morlotti.virtualbookcase.userwebsite.beans;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class BookGenre implements java.io.Serializable {

     private Integer id;
     private Genre genre;
     private BookDescription bookdescription;
     private Date created;

    }


