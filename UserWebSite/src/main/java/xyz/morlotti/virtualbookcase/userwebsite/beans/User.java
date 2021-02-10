package xyz.morlotti.virtualbookcase.userwebsite.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements java.io.Serializable
{
     private enum Sex
     {
          F("F"), H("H");

          private final String value;

          private Sex(String value) /* Une valeur d'enum est comme une classe, elle peut avoir un constructeur et des méthodes : https://stackoverflow.com/questions/13291076/java-enum-why-use-tostring-instead-of-name */
          {
               this.value = value;
          }

          @Override
          public String toString() /* Pour convertir une valeur d'enum en String */
          {
               return value;
          }

          public static Sex parseSex(String value) throws Exception /* Pour convertir une chaîne en une valeur d'enum */
          {
               /**/ if("F".equalsIgnoreCase(value))
               {
                    return F;
               }
               else if("H".equalsIgnoreCase(value))
               {
                    return H;
               }

               throw new Exception("Ne peut pas parser la valeur du sexe");
          }
     }

     private enum Role
     {
          ADMIN("ADMIN"),
          BATCH("BATCH"),
          EMPLOYEE("EMPLOYEE"),
          USER("USER"),
          GUEST("GUEST");

          private final String value;

          private Role(String value) /* Une valeur d'enum est comme une classe, elle peut avoir un constructeur et des méthodes : https://stackoverflow.com/questions/13291076/java-enum-why-use-tostring-instead-of-name */
          {
               this.value = value;
          }

          @Override
          public String toString() /* Pour convertir une valeur d'enum en String */
          {
               return value;
          }

          public static Role parseRole(String value) throws Exception /* Pour convertir une chaîne en une valeur d'enum */
          {
               /**/ if("ADMIN".equalsIgnoreCase(value))
               {
                    return ADMIN;
               }
               else if("BATCH".equalsIgnoreCase(value))
               {
                    return BATCH;
               }
               else if("EMPLOYEE".equalsIgnoreCase(value))
               {
                    return EMPLOYEE;
               }
               else if("USER".equalsIgnoreCase(value))
               {
                    return USER;
               }
               else if("BATCH".equalsIgnoreCase(value))
               {
                    return BATCH;
               }

               throw new Exception("Ne peut pas parser la valeur du rôle");
          }
     }

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

     private Sex sex;

     private Role role;

     private LocalDate membership;

     private Set<Loan> loans;

     private Date created;
}
