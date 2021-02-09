package xyz.morlotti.virtualbookcase.webapi.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "USER")
@Table(name="USER"
    ,catalog="virtualbookcase"
)
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

    public User() {
    }

    public User(String login, String password, String firstname, String lastname, String streetNb, String streetName,
                int postalCode, String city, String country, String email, Date birthdate, String sex, Date created, Date modified) {
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.streetNb = streetNb;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.email = email;
        this.birthdate = birthdate;
        this.sex = sex;
        this.created = created;
        this.modified = modified;
    }

    public User(String login, String password, String firstname, String lastname, String streetNb, String streetName,
                int postalCode, String city, String country, String email, Date birthdate, String sex, Date membership, Date created, Date modified, Set loans) {
       this.login = login;
       this.password = password;
       this.firstname = firstname;
       this.lastname = lastname;
       this.streetNb = streetNb;
       this.streetName = streetName;
       this.postalCode = postalCode;
       this.city = city;
       this.country = country;
       this.email = email;
       this.birthdate = birthdate;
       this.sex = sex;
       this.membership = membership;
       this.created = created;
       this.modified = modified;
       this.loans = loans;
    }

    public void initGuest()
    {
        Date now = new Date();

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
        this.birthdate = now;
        this.sex = "F";
        this.membership = now;
        this.created = now;
        this.modified = now;

        this.loans = new HashSet<>(0);
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

    @Column(name="login", nullable=false, length=64)
    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name="password", nullable=false, length=64)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="firstname", nullable=false, length=256)
    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name="lastname", nullable=false, length=256)
    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name="streetNb", nullable=false, length=32)
    public String getStreetNb() {
        return this.streetNb;
    }

    public void setStreetNb(String streetNb) {
        this.streetNb = streetNb;
    }

    @Column(name="streetName", nullable=false, length=256)
    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Column(name="postalCode", nullable=false)
    public int getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Column(name="city", nullable=false, length=256)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name="country", nullable=false, length=128)
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name="email", nullable=false, length=256)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="birthdate", nullable=false, length=10)
    public Date getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Column(name="sex", nullable=false, length=6)
    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="membership", length=19)
    public Date getMembership() { //Remplacer
        return this.membership;
    }

    public void setMembership(Date membership) {
        this.membership = membership;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created", nullable=false, length=19)
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified", nullable=false, length=19)
    public Date getModified() {
        return this.modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Loan> getLoans() {
        return this.loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }
}
