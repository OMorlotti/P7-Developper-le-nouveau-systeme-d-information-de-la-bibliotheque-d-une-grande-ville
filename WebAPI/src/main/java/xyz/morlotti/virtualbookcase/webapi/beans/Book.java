package xyz.morlotti.virtualbookcase.webapi.beans;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotFoundException;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


@Entity(name="BOOK")
@Table(name="BOOK"
    ,catalog="virtualbookcase"
)
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

     private Integer id;
     private BookDescription bookdescription;
     private String localId;
     private String condition;
     private boolean available;
     private Date created;
     private Set<Loan> loans = new HashSet(0);

    public Book() {
    }

    public Book(BookDescription bookdescription, String localId, Condition condition, boolean available, Date created) {
        this.bookdescription = bookdescription;
        this.localId = localId;
        this.condition = condition.toString();
        this.available = available;
        this.created = created;
    }

    public Book(BookDescription bookdescription, String localId, Condition condition, boolean available, Date created, Set loans) {
       this.bookdescription = bookdescription;
       this.localId = localId;
       this.condition = condition.toString();
       this.available = available;
       this.created = created;
       this.loans = loans;
    }
   
    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bookDescrFK", nullable=false)
    public BookDescription getBookdescription() {
        return this.bookdescription.;
    }
    
    public void setBookdescription(BookDescription bookdescription) {
        this.bookdescription = bookdescription;
    }

    @Column(name="localId", nullable=false, length=64)
    public String getLocalId() {
        return this.localId;
    }
    
    public void setLocalId(String localId) {
        this.localId = localId;
    }

    @Column(name="cond", nullable=false, length=4)
    public Condition getCondition() throws APINotFoundException
    {
        return Condition.parseCondition(this.condition);
    }

    public void setCondition(Condition condition) {
        this.condition = condition.toString();
    }

    @Column(name="available", nullable=false)
    public boolean isAvailable() {
        return this.available;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created", nullable=false, length=19)
    public Date getCreated() {
        return this.created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="book")
    public Set<Loan> getLoans() {
        return this.loans;
    }
    
    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }
}
