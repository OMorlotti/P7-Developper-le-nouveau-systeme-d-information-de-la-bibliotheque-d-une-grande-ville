package xyz.morlotti.virtualbookcase.userwebsite.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements java.io.Serializable
{
     private Integer id;

     private String login;

     private String password;

     private String firstname;

     private String lastname;

     private String streetNb;

     private String streetName;

     private int postalCode;

     private String city;

     private String country;

     private String email;

     private LocalDate birthdate;

     private String sex;

     private String role;

     private LocalDate membership;

     private String created;

     ////////

     private Set<Loan> loans;
}
