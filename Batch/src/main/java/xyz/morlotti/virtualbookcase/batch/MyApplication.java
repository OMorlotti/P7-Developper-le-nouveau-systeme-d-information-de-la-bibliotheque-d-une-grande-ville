package xyz.morlotti.virtualbookcase.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("xyz.morlotti.virtualbookcase.batch")
public class MyApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(MyApplication.class, args);
	}
}
