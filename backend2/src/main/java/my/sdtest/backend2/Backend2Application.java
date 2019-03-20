package my.sdtest.backend2;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Backend2Application {

	public static void main(String[] args) {
		SpringApplication.run(Backend2Application.class, args);
	}

	@Autowired JdbcTemplate jdbc;

	@PostConstruct
	public void setupTable() {

		jdbc.execute("CREATE TABLE T1 (id int , name varchar (20))");
		jdbc.execute("CREATE TABLE T2 (id int , name varchar (20))");
		System.out.println("Table Create..");

	}

}
