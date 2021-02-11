package xyz.morlotti.virtualbookcase.userwebsite.beans;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Loan implements java.io.Serializable
{
     private Integer id;

     private User user;

     private Book book;

     private LocalDate loanStartDate;

     private LocalDate loanEndDate;

     private Boolean extensionAsked;

     private String comment;
}
