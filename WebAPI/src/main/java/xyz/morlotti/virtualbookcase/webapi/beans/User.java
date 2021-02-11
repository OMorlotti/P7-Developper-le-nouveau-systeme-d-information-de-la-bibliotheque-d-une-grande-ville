package xyz.morlotti.virtualbookcase.webapi.beans;

import java.time.LocalDate;
import java.util.Set;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Stream;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USER")
@Table(name = "USER", catalog = "virtualbookcase")
public class User implements java.io.Serializable
{
    public enum Sex
    {
        F("F", 0), H("H", 1);

        private final String value;
        private final int code;

        private Sex(String value, int code) /* Une valeur d'enum est comme une classe, elle peut avoir un constructeur et des méthodes : https://stackoverflow.com/questions/13291076/java-enum-why-use-tostring-instead-of-name */
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

        public static Sex parseString(String value) /* Pour convertir une chaîne en une valeur d'enum */
        {
            return Stream.of(values()).filter(x -> x.value.equalsIgnoreCase(value)).findFirst().orElse(null);
        }

        public static Sex parseCode(int code) /* Pour convertir un entier en une valeur d'enum */
        {
            return Stream.of(values()).filter(x -> x.code == code).findFirst().orElse(null);
        }
    }

    ////////

    public enum Role
    {
        ADMIN("ADMIN", 0),
        BATCH("BATCH", 1),
        EMPLOYEE("EMPLOYEE", 2),
        USER("USER", 3),
        GUEST("GUEST", 4);

        private final String value;
        private final int code;

        private Role(String value, int code) /* Une valeur d'enum est comme une classe, elle peut avoir un constructeur et des méthodes : https://stackoverflow.com/questions/13291076/java-enum-why-use-tostring-instead-of-name */
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

        public static Role parseString(String value) /* Pour convertir une chaîne en une valeur d'enum */
        {
            return Stream.of(values()).filter(x -> x.value.equalsIgnoreCase(value)).findFirst().orElse(null);
        }

        public static Role parseCode(int code) /* Pour convertir un entier en une valeur d'enum */
        {
            return Stream.of(values()).filter(x -> x.code == code).findFirst().orElse(null);
        }
    }

    ////////

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
    private Integer sex;

    public Sex getSex()
    {
        return Sex.parseCode(this.sex);
    }

    public void setSex(Sex sex)
    {
        this.sex = sex.toCode();
    }

    @Column(name = "role", nullable = false)
    private Integer role;

    public Role getRole()
    {
        return Role.parseCode(this.role);
    }

    public void setRole(Role role)
    {
        this.role = role.toCode();
    }

    @Column(name = "membership", nullable = false)
    private LocalDate membership;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created;

    ////////

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Loan> loans;

    ////////

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
        this.sex = Sex.F.code;
        this.role = Role.GUEST.code;
        this.membership = localNow;
        this.created = now;

        this.loans = new HashSet<>(0);
    }
}
