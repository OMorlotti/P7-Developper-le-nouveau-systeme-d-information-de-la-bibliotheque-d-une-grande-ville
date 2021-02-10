package xyz.morlotti.virtualbookcase.userwebsite.beans;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter // création automatique des setters
@Getter // création automatique des getters
@AllArgsConstructor
@NoArgsConstructor
public class Book implements java.io.Serializable
{
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

    private BookDescription bookDescription;

    private String localId;

    private Condition condition;

    private boolean available;

    private Set<Loan> loans;

    private Date created;
}