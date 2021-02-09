package xyz.morlotti.virtualbookcase.webapi.beans;


import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity(name = "GENRE")
@Table(name="GENRE"
    ,catalog="virtualbookcase"
)
public class Genre implements java.io.Serializable {

     private Integer id;
     private String name;
     private Date created;
     private Set<BookGenre> bookGenres = new HashSet<>(0);

    public Genre() {
    }

    public Genre(String name, Date created) {
        this.name = name;
        this.created = created;
    }
    public Genre(String name, Date created, Set bookGenres) {
       this.name = name;
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

    @Column(name="name", nullable=false, length=128)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created", nullable=false, length=19)
    public Date getCreated() {
        return this.created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="genre")
    public Set<BookGenre> getBookGenres() {
        return this.bookGenres;
    }

    public void setBookGenres(Set<BookGenre> bookGenres) {
        this.bookGenres = bookGenres;
    }
  }


