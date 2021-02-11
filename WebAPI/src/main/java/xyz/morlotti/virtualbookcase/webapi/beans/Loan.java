package xyz.morlotti.virtualbookcase.webapi.beans;

import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "LOAN")
@Table(name = "LOAN", catalog = "virtualbookcase")
public class Loan implements java.io.Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userFK", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookFK", nullable = false)
    private Book book;

    @Column(name = "loanStartDate", nullable = false)
    private LocalDate loanStartDate;

    @Column(name = "loanEndDate", nullable = true)
    private LocalDate loanEndDate;

    @Column(name = "extensionAsked", nullable = false)
    private Boolean extensionAsked;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;
}
