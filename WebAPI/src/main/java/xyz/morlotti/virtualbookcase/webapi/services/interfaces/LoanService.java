package xyz.morlotti.virtualbookcase.webapi.services.interfaces;

import xyz.morlotti.virtualbookcase.webapi.beans.Loan;

import java.util.Optional;

public interface LoanService
{
	public Iterable<Loan> listLoans();

	public Optional<Loan> getLoan(int id);

	public Loan addLoan(Loan genre);

	public Loan updateLoan(int id, Loan genre);

	public void deleteLoan(int id);
}