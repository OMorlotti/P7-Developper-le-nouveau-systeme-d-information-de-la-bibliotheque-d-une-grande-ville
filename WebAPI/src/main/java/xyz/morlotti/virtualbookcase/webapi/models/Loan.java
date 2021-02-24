package xyz.morlotti.virtualbookcase.webapi.models;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static java.time.temporal.ChronoUnit.DAYS;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

    public String getState()
    {
        if(loanEndDate == null)
        {
            long delta = DAYS.between(loanStartDate, LocalDate.now());

            if(extensionAsked == false)
            {
                /**/ if(delta >= 30)
                {
                    return "NO_EXTENSION_IN_LATE";
                }
                else if(delta >= 15)
                {
                    return "ASK_EXTENSION";
                }
                else
                {
                    return "NO_EXTENSION";
                }
            }
            else
            {
                /**/ if(delta >= 60)
                {
                    return "EXTENSION_ASKED_IN_LATE";
                }
                else
                {
                    return "EXTENSION_ASKED";
                }
            }
        }
        else
        {
            return "RETURNED";
        }
    }

    public String getLogin() // getUser() is not present in the JSON for avoiding recursivity error
    {
        return user.getLogin();
    }

    public String getEmail() // getUser() is not present in the JSON for avoiding recursivity error
    {
        return user.getEmail();
    }
}
