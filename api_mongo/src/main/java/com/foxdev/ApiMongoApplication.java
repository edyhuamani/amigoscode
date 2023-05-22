package com.foxdev;

import com.foxdev.model.Address;
import com.foxdev.model.Gender;
import com.foxdev.model.Student;
import com.foxdev.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
@ComponentScan({"com.foxdev.repositories","com.foxdev.*"})
public class ApiMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiMongoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate){
		return args->{
			Address address=new Address(
					"England",
					"London",
					"NE9"
			);
			BigDecimal spend=new BigDecimal("30.20");

			String email="jdajabre292@gmail.com";
			Student student=new Student(
					"Jamila",
					"Dajabre",
					email,
					Gender.FEMALE,
					address,
					List.of("Computer Science","Math Science"),
					spend,
					LocalDateTime.now()
			);
			//repository.save(student);
			//repository.insert(student);
			//FORMA MANUAL DE BUSCAR

			//usingMongoDbTemplatequery(repository, mongoTemplate, email, student);

			repository.findStudentByEmail(email)
					.ifPresentOrElse(s->{
						System.out.println(s+" already exists ");
					},()->{
						System.out.println("Inserting student "+student);
						repository.insert(student);
					});


		};
	}

	private static void usingMongoDbTemplatequery(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
		Query query=new Query();
		query.addCriteria(Criteria.where("email").is(email));
		List<Student> studentQuery = mongoTemplate.find(query,Student.class);

		if (studentQuery.size()>1){
			throw new IllegalStateException(
					"found many students with email"+ email
			);
		}
		if (studentQuery.isEmpty()){
			repository.insert(student);
		} else {
			System.out.println("Student existent....");
		}
	}

}
