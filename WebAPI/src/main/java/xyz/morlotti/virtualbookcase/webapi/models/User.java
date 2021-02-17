package xyz.morlotti.virtualbookcase.webapi.models;

import java.time.LocalDate;
import java.util.Set;
import java.util.Date;
import java.util.HashSet;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "USER")
@Table(name = "USER", catalog = "virtualbookcase")
public class User implements java.io.Serializable
{
    // According BCryptPasswordEncoder class from Spring, you could check if a String is encoded or not using this regex pattern

    static Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

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
            return Stream.of(values()).filter(x -> x.value.equalsIgnoreCase(value)).findFirst().orElse(F);
        }

        public static Sex parseCode(int code) /* Pour convertir un entier en une valeur d'enum */
        {
            return Stream.of(values()).filter(x -> x.code == code).findFirst().orElse(F);
        }
    }

    ////////

    public enum Role
    {
        ADMIN("ADMIN", 0),
        EMPLOYEE("EMPLOYEE", 1),
        USER("USER", 2),
        GUEST("GUEST", 3);

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
            return Stream.of(values()).filter(x -> x.value.equalsIgnoreCase(value)).findFirst().orElse(GUEST);
        }

        public static Role parseCode(int code) /* Pour convertir un entier en une valeur d'enum */
        {
            return Stream.of(values()).filter(x -> x.code == code).findFirst().orElse(GUEST);
        }
    }

    ////////

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "login", nullable = false, length = 64)
    private String login;

    // Pour ne pas exposer le password lors d'un GET
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
/*
    ////////

    public String getPassword()
    {
        if(!BCRYPT_PATTERN.matcher(this.password).matches())
        {
            return new BCryptPasswordEncoder().encode(password);
        }
        else
        {
            return this.password;
        }
    }

    public void setPassword(String password)
    {
        if(password != null)
        {
            if(!BCRYPT_PATTERN.matcher(password).matches())
            {
                if(!new BCryptPasswordEncoder().matches("", password))
                {
                    this.password = new BCryptPasswordEncoder().encode(password);
                }
            }
            else
            {
                if(!password.isEmpty())
                {
                    this.password = password;
                }
            }
        }
    }
*/
}
