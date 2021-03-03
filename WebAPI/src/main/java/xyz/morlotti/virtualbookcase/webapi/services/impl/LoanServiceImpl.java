package xyz.morlotti.virtualbookcase.webapi.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.morlotti.virtualbookcase.webapi.models.Book;
import xyz.morlotti.virtualbookcase.webapi.models.Loan;
import xyz.morlotti.virtualbookcase.webapi.daos.BookDAO;
import xyz.morlotti.virtualbookcase.webapi.daos.LoanDAO;
import xyz.morlotti.virtualbookcase.webapi.services.interfaces.LoanService;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotCreatedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotDeletedException;

import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService
{
	@Autowired
	private LoanDAO loanDAO;

	@Autowired
	private BookDAO bookDAO;

	public Iterable<Loan> listLoans()
	{
		Iterable<Loan> loans = loanDAO.findAll();

		return loans;
	}

	public Iterable<Loan> listLoansInLate()
	{
		Iterable<Loan> loans = loanDAO.findInLate();

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

		/* Update the book's availability */

		Book book = loan.getBook();

		if(book.isAvailable() != (loan.getLoanEndDate() != null))
		{
			book.setAvailable(loan.getLoanEndDate() != null);

			Book newBook = bookDAO.save(book);

			if(newBook == null)
			{
				throw new APINotCreatedException("Book not updated");
			}
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
