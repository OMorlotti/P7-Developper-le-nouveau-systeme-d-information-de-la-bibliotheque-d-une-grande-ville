package xyz.morlotti.virtualbookcase.userwebsite.beans;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loan implements java.io.Serializable
{
     private Integer id;

     private User user;

     private Book book;

     private LocalDate loanStartDate;

     private LocalDate loanEndDate;

     private Boolean prolongationAsked;

     private String comment;
}
