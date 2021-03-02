package xyz.morlotti.virtualbookcase.userwebsite.beans;

import java.util.Date;
import java.util.List;

import lombok.*;

@Setter
@Getter
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

    private List<Loan> loans;
}