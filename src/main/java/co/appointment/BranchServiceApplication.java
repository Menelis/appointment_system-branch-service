package co.appointment;

import co.appointment.shared.service.EncryptionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.core.context.SecurityContext;

@SpringBootApplication
@ComponentScan(
		excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = EncryptionService.class)})
public class BranchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BranchServiceApplication.class, args);
	}

}
