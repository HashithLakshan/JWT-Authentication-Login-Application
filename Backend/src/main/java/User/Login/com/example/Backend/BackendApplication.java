package User.Login.com.example.Backend;

import User.Login.com.example.Backend.constant.CommonStatus;
import User.Login.com.example.Backend.constant.ERole;
import User.Login.com.example.Backend.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.management.relation.Role;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	

}
