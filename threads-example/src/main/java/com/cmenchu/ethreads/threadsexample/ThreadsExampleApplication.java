package com.cmenchu.ethreads.threadsexample;

import com.cmenchu.ethreads.threadsexample.runnables.ReportRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cmenchu")
public class ThreadsExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreadsExampleApplication.class, args);
	}

}
