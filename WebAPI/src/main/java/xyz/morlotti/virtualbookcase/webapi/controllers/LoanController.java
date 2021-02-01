package xyz.morlotti.virtualbookcase.webapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import xyz.morlotti.virtualbookcase.webapi.beans.Loan;
import xyz.morlotti.virtualbookcase.webapi.daos.LoanDAO;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotCreatedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotDeletedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotFoundException;

import java.net.URI;
import java.util.Optional;

@RestController
public class LoanController
{
	@Autowired
	private LoanDAO loanDAO;

	@RequestMapping(value="/loans", method = RequestMethod.GET)
	public Iterable<Loan> listLoans()
	{
		Iterable<Loan> loans = loanDAO.findAll();

		return loans;
	}

	@RequestMapping(value="/loan/{id}", method = RequestMethod.GET)
	public Optional<Loan> getLoan(@PathVariable int id)
	{
		Optional<Loan> optional = loanDAO.findById(id);

		optional.orElseThrow(() -> new APINotFoundException("Loan " + id + " not found"));

		return optional;
	}

	@RequestMapping(value = "/loan", method = RequestMethod.POST)
	public ResponseEntity<Void> addLoan(@RequestBody Loan loan)
	{
		Loan newLoan = loanDAO.save(loan);

		if(newLoan == null)
		{
			throw new APINotCreatedException("Loan not inserted");
		}

		URI location = ServletUriComponentsBuilder
           .fromCurrentRequest()
           .path("/{id}")
           .buildAndExpand(newLoan.getId())
           .toUri()
		;

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value="/loan/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateLoan(@PathVariable int id, @RequestBody Loan loan)
	{
		Loan existingLoan = getLoan(id).get();

		existingLoan.setComment(loan.getComment());

		existingLoan.setCondition(loan.getComment());

		existingLoan.setLoanEndDate(loan.getLoanEndDate());

		existingLoan.setProlongationAsked(loan.isProlongationAsked());

		return addLoan(existingLoan);
	}

	@RequestMapping(value="/loan/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteLoan(@PathVariable int id)
	{
		try
		{
			loanDAO.deleteById(id);

			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new APINotDeletedException("Loan " + id + " not deleted: " + e.getMessage());
		}
	}
}
