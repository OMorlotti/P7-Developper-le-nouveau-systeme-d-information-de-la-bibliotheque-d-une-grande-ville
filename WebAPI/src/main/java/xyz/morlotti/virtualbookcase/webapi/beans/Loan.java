package xyz.morlotti.virtualbookcase.webapi.beans;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity(name = "LOAN")
@Table(name="LOAN"
    ,catalog="virtualbookcase"
)
public class Loan implements java.io.Serializable {

     private Integer id;
     private User user;
     private Book book;
     private Date loanStartDate;
     private Date loanEndDate;
     private boolean prolongationAsked;
     private String condition;
     private String comment;

    public Loan() {
    }

    public Loan(User user, Book book, Date loanStartDate, boolean prolongationAsked) {
        this.user = user;
        this.book = book;
        this.loanStartDate = loanStartDate;
        this.prolongationAsked = prolongationAsked;
    }

    public Loan(User user, Book book, Date loanStartDate, Date loanEndDate, boolean prolongationAsked,
                String condition, String comment) {
       this.user = user;
       this.book = book;
       this.loanStartDate = loanStartDate;
       this.loanEndDate = loanEndDate;
       this.prolongationAsked = prolongationAsked;
       this.condition = condition;
       this.comment = comment;
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
    @JoinColumn(name="userFK", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bookFK", nullable=false)
    public Book getBook() {
        return this.book;
    }
    
    public void setBook(Book book) {
        this.book = book;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="loanStartDate", nullable=false, length=19)
    public Date getLoanStartDate() {
        return this.loanStartDate;
    }
    
    public void setLoanStartDate(Date loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="loanEndDate", length=19)
    public Date getLoanEndDate() {
        return this.loanEndDate;
    }
    
    public void setLoanEndDate(Date loanEndDate) {
        this.loanEndDate = loanEndDate;
    }

    @Column(name="prolongationAsked", nullable=false)
    public boolean isProlongationAsked() {
        return this.prolongationAsked;
    }
    
    public void setProlongationAsked(boolean prolongationAsked) {
        this.prolongationAsked = prolongationAsked;
    }

    @Column(name="cond", length=4)
    public String getCondition() {
        return this.condition;
    }
    
    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Column(name="comment", columnDefinition="TEXT")
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
}


