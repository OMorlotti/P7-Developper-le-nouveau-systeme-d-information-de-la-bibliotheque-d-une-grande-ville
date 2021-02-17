package xyz.morlotti.virtualbookcase.userwebsite.beans;

import java.time.LocalDate;

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
