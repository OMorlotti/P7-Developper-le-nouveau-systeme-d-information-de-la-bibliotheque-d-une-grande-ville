package xyz.morlotti.virtualbookcase.webapi.beans;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotFoundException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USER")
@Table(name = "USER", catalog = "virtualbookcase")
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

        public static Sex parseSex(String value) throws APINotFoundException /* Pour convertir une chaîne en une valeur d'enum */
        {
            /**/ if("F".equalsIgnoreCase(value))
            {
                return F;
            }
            else if("H".equalsIgnoreCase(value))
            {
                return H;
            }

            throw new APINotFoundException("Ne peut pas parser la valeur du sexe");
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

        public static Role parseRole(String value) throws APINotFoundException /* Pour convertir une chaîne en une valeur d'enum */
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

            throw new APINotFoundException("Ne peut pas parser la valeur du rôle");
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "login", nullable = false, length = 64)
    private String login;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "firstname", nullable = false, length = 256)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 256)
    private String lastname;

    @Column(name = "streetNb", nullable = false, length = 32)
    private String streetNb;

    @Column(name = "streetName", nullable = false, length = 256)
    private String streetName;

    @Column(name = "postalCode", nullable = false)
    private int postalCode;

    @Column(name = "city", nullable = false, length = 256)
    private String city;

    @Column(name = "country", nullable = false, length = 128)
    private String country;

    @Column(name = "email", nullable = false, length = 256)
    private String email;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "sex", nullable = false)
    private Sex sex;

    @Column(name = "role", nullable = false, columnDefinition = "INT DEFAULT 4") // 4 pour GUEST
    private Role role;

    @Column(name = "membership", nullable = false)
    private LocalDate membership;

    @JsonIgnoreProperties("user") //
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Loan> loans;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created;

    public void initGuest()
    {
        Date now = new Date();

        LocalDate localNow = new java.sql.Date(now.getTime()).toLocalDate();

        this.login = "guest";
        this.password = "guest";
        this.firstname = "guest";
        this.lastname = "guest";
        this.streetNb = "N/A";
        this.streetName = "N/A";
        this.postalCode = 0;
        this.city = "N/A";
        this.country = "N/A";
        this.email = "N/A";
        this.birthdate = localNow;
        this.sex = Sex.F;
        this.role = Role.GUEST;
        this.membership = localNow;
        this.created = now;

        this.loans = new HashSet<>(0);
    }
}
