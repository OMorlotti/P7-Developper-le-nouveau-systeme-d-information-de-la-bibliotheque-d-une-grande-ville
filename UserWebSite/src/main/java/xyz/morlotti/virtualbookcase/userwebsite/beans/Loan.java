package xyz.morlotti.virtualbookcase.userwebsite.beans;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class Loan implements java.io.Serializable {

     private Integer id;
     private User user;
     private Book book;
     private Date loanStartDate;
     private Date loanEndDate;
     private boolean prolongationAsked;
     private String condition;
     private String comment;

}


