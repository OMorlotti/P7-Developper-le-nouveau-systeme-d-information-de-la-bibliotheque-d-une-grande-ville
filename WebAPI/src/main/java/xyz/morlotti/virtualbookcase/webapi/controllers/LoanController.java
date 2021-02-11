package xyz.morlotti.virtualbookcase.webapi.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import xyz.morlotti.virtualbookcase.webapi.beans.Loan;
import xyz.morlotti.virtualbookcase.webapi.services.interfaces.LoanService;

@RestController
public class LoanController
{
	@Autowired
	LoanService loanService;

	@RequestMapping(value = "/loans", method = RequestMethod.GET)
	public Iterable<Loan> listLoans()
	{
		return loanService.listLoans();
	}

	@RequestMapping(value = "/loan/{id}", method = RequestMethod.GET)
	public Optional<Loan> getLoan(@PathVariable int id)
	{
		return loanService.getLoan(id);
	}

	@RequestMapping(value = "/loan", method = RequestMethod.POST)
	public ResponseEntity<Void> addLoan(@RequestBody Loan loan)
	{
		Loan newLoan = loanService.addLoan(loan);

		URI location = ServletUriComponentsBuilder
		   .fromCurrentRequest()
		   .path("/{id}")
		   .buildAndExpand(newLoan.getId())
		   .toUri()
		;

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "/loan/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateLoan(@PathVariable int id, @RequestBody Loan loan)
	{
		Loan newLoan = loanService.updateLoan(id, loan);

		URI location = ServletUriComponentsBuilder
		   .fromCurrentRequest()
		   .path("/{id}")
		   .buildAndExpand(newLoan.getId())
		   .toUri()
		;

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "/loan/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteLoan(@PathVariable int id)
	{
		loanService.deleteLoan(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
