package xyz.morlotti.virtualbookcase.webapi.beans;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotFoundException;

@Getter // génère automatiquement les getters
@Setter // génère automatiquement les setters
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BOOK")
@Table(name = "BOOK", catalog = "virtualbookcase")
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

        public static Condition parseCondition(String value) throws APINotFoundException /* Pour convertir une chaîne en une valeur d'enum */
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

            throw new APINotFoundException("Ne peut pas parser la valeur de la condition");
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookDescriptionFK", nullable = false)
    private BookDescription bookDescription;

    @Column(name = "localId", nullable = false, length = 64)
    private String localId;

    @Column(name = "cond", nullable = false)
    private Condition condition;

    @Column(name = "available", nullable = false)
    private boolean available;

    @JsonIgnoreProperties("book") // Evite la récursivité infinie
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
    private Set<Loan> loans;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created;
}
