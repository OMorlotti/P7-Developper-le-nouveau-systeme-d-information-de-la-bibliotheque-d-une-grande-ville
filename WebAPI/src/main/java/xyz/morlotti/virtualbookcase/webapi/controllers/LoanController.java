package xyz.morlotti.virtualbookcase.webapi.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import xyz.morlotti.virtualbookcase.webapi.exceptions.APiNotAuthorizedException;
import xyz.morlotti.virtualbookcase.webapi.models.Loan;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotFoundException;
import xyz.morlotti.virtualbookcase.webapi.models.User;
import xyz.morlotti.virtualbookcase.webapi.security.services.UserDetailsImpl;
import xyz.morlotti.virtualbookcase.webapi.services.interfaces.LoanService;

@RestController
public class LoanController
{
	@Autowired
	LoanService loanService;

	@PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
	@RequestMapping(value = "/loans", method = RequestMethod.GET)
	public Iterable<Loan> listLoans()
	{
		return loanService.listLoans();
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE') or hasRole('USER')")
	@RequestMapping(value = "/loan/{id}", method = RequestMethod.GET)
	public Loan getLoan(@PathVariable int id, Authentication authentication)
	{
		Optional<Loan> optional = loanService.getLoan(id);

		if(!optional.isPresent())
		{
			throw new APINotFoundException("Loan " + id + " not found");
		}

		return checkRole(optional.get(), authentication);
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
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

	@PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
	@RequestMapping(value = "/loan/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateLoan(@PathVariable int id, @RequestBody Loan loan, Authentication authentication)
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

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/loan/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteLoan(@PathVariable int id)
	{
		loanService.deleteLoan(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	////////

	private Loan checkRole(Loan loan, Authentication authentication)
	{
		/* Check if the operation is authorized */

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		if(loan.getUser().getId() != userDetails.getId() && userDetails.getAuthority() == User.Role.USER)
		{
			throw new APiNotAuthorizedException("The current user is not the borrower");
		}

		return loan;
	}
}
