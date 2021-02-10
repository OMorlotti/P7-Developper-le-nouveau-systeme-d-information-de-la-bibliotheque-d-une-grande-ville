package xyz.morlotti.virtualbookcase.webapi.daos;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import xyz.morlotti.virtualbookcase.webapi.beans.Loan;

@Repository
public interface LoanDAO extends JpaRepository<Loan, Integer>
{

}
