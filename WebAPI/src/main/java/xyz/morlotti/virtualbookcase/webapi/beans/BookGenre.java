package xyz.morlotti.virtualbookcase.webapi.beans;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity(name = "BOOK_GENRE")
@Table(name="BOOK_GENRE"
    ,catalog="virtualbookcase"
)
public class BookGenre  implements java.io.Serializable {


     private Integer id;
     private Genre genre;
     private BookDescription bookdescription;
     private Date created;

    public BookGenre() {
    }

    public BookGenre(Genre genre, BookDescription bookdescription, Date created) {
       this.genre = genre;
       this.bookdescription = bookdescription;
       this.created = created;
    }
   
    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="genreFK", nullable=false)
    public Genre getGenre() {
        return this.genre;
    }
    
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bookDescrFK", nullable=false)
    public BookDescription getBookdescription() {
        return this.bookdescription;
    }
    
    public void setBookdescription(BookDescription bookdescription) {
        this.bookdescription = bookdescription;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created", nullable=false, length=19)
    public Date getCreated() {
        return this.created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
}


