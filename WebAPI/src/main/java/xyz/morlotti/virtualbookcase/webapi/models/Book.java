package xyz.morlotti.virtualbookcase.webapi.models;

import java.util.Set;
import java.util.Date;
import java.util.stream.Stream;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

import lombok.*;

import org.springframework.data.annotation.CreatedDate;

@Getter // génère automatiquement les getters
@Setter // génère automatiquement les setters
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "BOOK")
@Table(name = "BOOK", catalog = "virtualbookcase")
public class Book implements java.io.Serializable
{
    private enum Condition
    {
        NEW("NEW", 0), GOOD("GOOD", 1), BAD("BAD", 2), DEAD("DEAD", 3);

        private final String value;
        private final int code;

        private Condition(String value, int code) /* Une valeur d'enum est comme une classe, elle peut avoir un constructeur et des méthodes : https://stackoverflow.com/questions/13291076/java-enum-why-use-tostring-instead-of-name */
        {
            this.value = value;
            this.code = code;
        }

        public String toString() /* Pour convertir une valeur d'enum en String */
        {
            return value;
        }

        public int toCode() /* Pour convertir une valeur d'enum en entier */
        {
            return code;
        }

        public static Condition parseString(String value) /* Pour convertir une chaîne en une valeur d'enum */
        {
            return Stream.of(values()).filter(x -> x.value.equalsIgnoreCase(value)).findFirst().orElse(null);
        }

        public static Condition parseCode(int code) /* Pour convertir un entier en une valeur d'enum */
        {
            return Stream.of(values()).filter(x -> x.code == code).findFirst().orElse(null);
        }
    }

    ////////

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "localId", nullable = false, length = 64)
    private String localId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookDescriptionFK", nullable = false)
    private BookDescription bookDescription;

    @Column(name = "cond", nullable = false)
    private Integer condition;

    public Condition getCondition()
    {
        return Condition.parseCode(this.condition);
    }

    public void setCondition(Condition condition)
    {
        this.condition = condition.toCode();
    }

    @Column(name = "available", nullable = false)
    private boolean available;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created;

    ////////

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
    private Set<Loan> loans;
}
