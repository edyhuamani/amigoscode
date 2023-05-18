package com.amigoscode;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@ComponentScan("com.amigoscode.config")
//@EnableKafka
public class KafkaExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaExampleApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkatemplate) {
		return args -> {
			kafkatemplate.send("amigoscode","hello kafka");
		};
	}
	
	
	@Bean
	CommandLineRunner commandLineRunner2(KafkaTemplate<String, String> kafkatemplate) {
		return args -> {
			//kafkatemplate.send("amigoscode","hello kafka 2");
			//kafkatemplate.send("amigoscode","hello kafka 3");
			//kafkatemplate.send("amigoscode","hello kafka 4");
			
			for(int i=0;i<10_000_000;i++) {
				kafkatemplate.send("amigoscode","hello kafka "+i);
			}
		};
	}

}
