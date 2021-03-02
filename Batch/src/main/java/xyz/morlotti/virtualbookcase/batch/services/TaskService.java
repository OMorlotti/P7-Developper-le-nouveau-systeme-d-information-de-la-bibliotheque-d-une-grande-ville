package xyz.morlotti.virtualbookcase.batch.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;

import xyz.morlotti.virtualbookcase.batch.beans.Loan;
import xyz.morlotti.virtualbookcase.batch.EmailSender;
import xyz.morlotti.virtualbookcase.batch.MyFeignProxy;

@Service
@EnableAsync
@EnableScheduling
public class TaskService
{
	private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

	@Value("${virtualbookcase.app.batch.login}")
	private String login;

	@Value("${virtualbookcase.app.batch.password}")
	private String password;

	@Autowired
	private MyFeignProxy myFeignProxy;

	@Autowired
	private EmailSender emailSender;

	// See: https://riptutorial.com/spring/example/21209/cron-expression
	@Scheduled(cron = "0 10 * * * *") // Tous les jours à 10h
	public void mainTask()
	{
		String token = myFeignProxy.login(login, password);

		List<Loan> loans = myFeignProxy.listLoansInLate(token);

		for(Loan loan: loans)
		{
			try
			{
				emailSender.sendMessage(
					loan.getEmail(),
					loan.getEmail(),
					"",
					"Retour de livre en retard de " + (-loan.getRemainingDays()) + " jour(s)",
					"Bonjour " + loan.getLogin() + "\nLe retour du livre " + loan.getBook().getBookDescription().getTitle() + " est hors délais de "+ (-loan.getRemainingDays()) + " jour(s).\n" +
						"Veuillez effectuer le retour au plus vite !,\n" +
						"Cordialement,\n" +
						"Votre bibliothèque"
				);
			}
			catch(Exception e)
			{
				logger.error("L'email " + loan.getEmail() + " destiné à " + loan.getLogin() + " n'a pas pu être envoyé (livre #" + loan.getBook().getId() + ")", e);
			}
		}
	}
}
