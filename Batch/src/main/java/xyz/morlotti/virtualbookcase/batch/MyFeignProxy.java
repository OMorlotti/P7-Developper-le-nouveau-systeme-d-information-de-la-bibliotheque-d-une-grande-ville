package xyz.morlotti.virtualbookcase.batch;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;

import xyz.morlotti.virtualbookcase.batch.beans.Loan;

@FeignClient(name = "myFeignProxy", url = "localhost:9090")
public interface MyFeignProxy
{
	@GetMapping("/loans/in-late")
	public List<Loan> listLoansInLate();
}
