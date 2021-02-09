package xyz.morlotti.virtualbookcase.userwebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("xyz.morlotti.virtualbookcase.userwebsite")
public class MyApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(MyApplication.class, args);
	}
}
