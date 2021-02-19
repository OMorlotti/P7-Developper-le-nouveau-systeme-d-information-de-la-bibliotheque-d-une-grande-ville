package xyz.morlotti.virtualbookcase.webapi.models;

import java.util.Set;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.CreatedDate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "BOOKDESCRIPTION")
@Table(name = "BOOKDESCRIPTION", catalog = "virtualbookcase")
public class BookDescription implements java.io.Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "isbn", nullable = false, length = 13)
    @Size(min = 10, max = 13, message = "L'ISBN doit comporter entre 10 et 13 caractères")
    private String isbn;

    @Column(name = "title", nullable = false, length = 256)
    private String title;

    @Column(name = "authorFirstname", nullable = false, length = 128)
    private String authorFirstname;

    @Column(name = "authorLastname", nullable = false, length = 128)
    private String authorLastname;

    @Column(name = "editor", nullable = false, length = 128)
    private String editor;

    @Column(name = "editionNumber", nullable = false)
    private Integer editionNumber;

    @Column(name = "editionYear", nullable = false, columnDefinition = "YEAR")
    @Size(min = 4, max = 4, message = "L'année d'édition doit compter 4 chiffres")
    private Integer editionYear;

    @Column(name = "genre", nullable = false, length = 64)
    private String genre;

    @Column(name = "format", nullable = false, length = 64)
    private String format;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created;

    ////////

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bookDescription")
    private Set<Book> books;
}
