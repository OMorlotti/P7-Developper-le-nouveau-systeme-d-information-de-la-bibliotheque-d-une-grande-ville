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
public class Genre implements java.io.Serializable
{
     private Integer id;
     private String name;
     private Date created;
     private Set<BookGenre> bookGenres = new HashSet<>(0);
}
