package xyz.morlotti.virtualbookcase.webapi.beans;


import com.fasterxml.jackson.annotation.*;

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


@Entity(name="BOOKDESCRIPTION")
@Table(name="BOOKDESCRIPTION"
    ,catalog="virtualbookcase"
)
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
     //private Set<Book> books = new HashSet<>(0);

    public BookDescription() {
    }

    public BookDescription(String isbn, String title, String authorFirstname, String authorLastname, String editor, Integer edition, Integer editionYear, String format, Date created, Date modified) {
        this.isbn = isbn;
        this.title = title;
        this.authorFirstname = authorFirstname;
        this.authorLastname = authorLastname;
        this.editor = editor;
        this.edition = edition;
        this.editionYear = editionYear;
        this.format = format;
        this.created = created;
        this.modified = modified;
    }
    public BookDescription(String isbn, String title, String authorFirstname, String authorLastname, String editor, Integer edition, Integer editionYear, String format, String comment, Date created, Date modified, Set bookGenres, Set books) {
       this.isbn = isbn;
       this.title = title;
       this.authorFirstname = authorFirstname;
       this.authorLastname = authorLastname;
       this.editor = editor;
       this.edition = edition;
       this.editionYear = editionYear;
       this.format = format;
       this.comment = comment;
       this.created = created;
       this.modified = modified;
       this.bookGenres = bookGenres;
       //this.books = books;
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

    @Column(name="isbn", nullable=false, length=64)
    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Column(name="title", nullable=false, length=256)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="authorFirstname", nullable=false, length=128)
    public String getAuthorFirstname() {
        return this.authorFirstname;
    }

    public void setAuthorFirstname(String authorFirstname) {
        this.authorFirstname = authorFirstname;
    }

    @Column(name="authorLastname", nullable=false, length=128)
    public String getAuthorLastname() {
        return this.authorLastname;
    }

    public void setAuthorLastname(String authorLastname) {
        this.authorLastname = authorLastname;
    }

    @Column(name="editor", nullable=false, length=128)
    public String getEditor() {
        return this.editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    @Column(name="edition", nullable=false)
    public Integer getEdition() {
        return this.edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    @Column(name="editionYear", nullable=false)
    public Integer getEditionYear() {
        return this.editionYear;
    }

    public void setEditionYear(Integer editionYear) {
        this.editionYear = editionYear;
    }

    @Column(name="format", nullable=false, length=11)
    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Column(name="comment", columnDefinition="TEXT")
    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created", nullable=false, length=19)
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified", nullable=false, length=19)
    public Date getModified() {
        return this.modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="bookdescription")
    public Set<BookGenre> getBookGenres() {
        return this.bookGenres;
    }

    public void setBookGenres(Set<BookGenre> bookGenres) {
        this.bookGenres = bookGenres;
    }

    //@JsonIgnore
    //@OneToMany(fetch=FetchType.LAZY, mappedBy="bookdescription")
    //public Set<Book> getBooks() {
    //    return this.books;
    //}

    //public void setBooks(Set<Book> books) {
    //    this.books = books;
    //}
}


