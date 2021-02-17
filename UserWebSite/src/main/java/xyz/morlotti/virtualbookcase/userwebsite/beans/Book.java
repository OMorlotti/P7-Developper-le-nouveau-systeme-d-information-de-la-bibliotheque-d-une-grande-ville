package xyz.morlotti.virtualbookcase.userwebsite.beans;

import java.util.Date;
import java.util.Set;

import lombok.*;

@Setter // création automatique des setters
@Getter // création automatique des getters
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book implements java.io.Serializable
{
    private Integer id;

    private String localId;

    private BookDescription bookDescription;

    private String condition;

    private boolean available;

    private Date created;

    ////////

    private Set<Loan> loans;
}