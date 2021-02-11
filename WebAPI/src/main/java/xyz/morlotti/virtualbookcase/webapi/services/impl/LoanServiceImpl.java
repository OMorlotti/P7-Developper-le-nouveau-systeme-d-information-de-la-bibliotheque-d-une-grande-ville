package xyz.morlotti.virtualbookcase.webapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import xyz.morlotti.virtualbookcase.webapi.beans.Loan;
import xyz.morlotti.virtualbookcase.webapi.daos.LoanDAO;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotCreatedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotDeletedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotFoundException;
import xyz.morlotti.virtualbookcase.webapi.services.interfaces.LoanService;

import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService
{
	@Autowired
	private LoanDAO loanDAO;

	public Iterable<Loan> listLoans()
	{
		Iterable<Loan> loans = loanDAO.findAll();

		return loans;
	}

	public Optional<Loan> getLoan(int id)
	{
		Optional<Loan> optional = loanDAO.findById(id);

		return optional;
	}

	public Loan addLoan(Loan loan)
	{
		Loan newLoan = loanDAO.save(loan);

		if(newLoan == null)
		{
			throw new APINotCreatedException("Loan not inserted");
		}

		return newLoan;
	}

	public Loan updateLoan(int id, Loan loan)
	{
		Loan existingLoan = getLoan(id).get();

		existingLoan.setComment(loan.getComment());

		existingLoan.setLoanEndDate(loan.getLoanEndDate());

		existingLoan.setExtensionAsked(loan.getExtensionAsked());

		return addLoan(existingLoan);
	}

	public void deleteLoan(int id)
	{
		try
		{
			loanDAO.deleteById(id);
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new APINotDeletedException("Loan " + id + " not deleted: " + e.getMessage());
		}
	}
}
