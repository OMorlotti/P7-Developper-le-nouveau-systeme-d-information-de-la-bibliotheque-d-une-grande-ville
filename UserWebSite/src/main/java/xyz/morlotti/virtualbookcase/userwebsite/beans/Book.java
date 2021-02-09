package xyz.morlotti.virtualbookcase.userwebsite.beans;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter // création automatique des setters
@Getter // création automatique des getters
@NoArgsConstructor
public class Book implements java.io.Serializable {

    private enum Condition
    {
        NEW("NEW"), GOOD("GOOD"), BAD("BAD"), DEAD("DEAD");

        private final String value;

        private Condition(String value) /* Une valeur d'enum est comme une classe, elle peut avoir un constructeur et des méthodes : https://stackoverflow.com/questions/13291076/java-enum-why-use-tostring-instead-of-name */
        {
            this.value = value;
        }

        @Override
        public String toString() /* Pour convertir une valeur d'enum en String */
        {
            return value;
        }

        public static Condition parseCondition(String value) throws Exception /* Pour convertir une chaîne en une valeur d'enum */
        {
            /**/ if("NEW".equalsIgnoreCase(value))
            {
                return NEW;
            }
            else if("GOOD".equalsIgnoreCase(value))
            {
                return GOOD;
            }
            else if("BAD".equalsIgnoreCase(value))
            {
                return BAD;
            }
            else if("DEAD".equalsIgnoreCase(value))
            {
                return DEAD;
            }

            throw new Exception("Ne peut pas parser la valeur de la condition");
        }
    }

     private Integer id;
     private BookDescription bookdescription;
     private String localId;
     private String condition;
     private boolean available;
     private Date created;
     private Set<Loan> loans = new HashSet(0);

}