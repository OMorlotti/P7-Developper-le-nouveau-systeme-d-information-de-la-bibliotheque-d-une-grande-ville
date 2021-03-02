package xyz.morlotti.virtualbookcase.batch.beans;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Loan implements java.io.Serializable
{
     private Integer id;

     private Book book;

     private LocalDate loanStartDate;

     private LocalDate loanEndDate;

     private Boolean extensionAsked;

     private String comment;

     private String state;

     private long remainingDays;

     private LocalDate returnDate;

     private String login;

     private String email;
}
