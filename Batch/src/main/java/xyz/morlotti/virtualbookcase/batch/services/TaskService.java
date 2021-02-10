package xyz.morlotti.virtualbookcase.batch.services;

import org.springframework.stereotype.Service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Service
@EnableAsync
@EnableScheduling
public class TaskService
{
	// See: https://riptutorial.com/spring/example/21209/cron-expression
	@Scheduled(cron = "0 10 * * * *") // Tous les jours à 10h
	public void mainTask()
	{
		/* TODO */
	}
}
