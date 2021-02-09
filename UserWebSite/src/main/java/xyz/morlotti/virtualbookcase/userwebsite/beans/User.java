package xyz.morlotti.virtualbookcase.userwebsite.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class User implements java.io.Serializable {

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
     private Date birthdate;
     private String sex;
     private Date membership;
     private Date created;
     private Date modified;
     private Set<Loan> loans = new HashSet<>(0);
}
