package MODULITH_SERVICES.AN.UNPUBLISHED.PROJECT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"MODULITH_SERVICES.AN.UNPUBLISHED.PROJECT","RestApi","SAVETODB","SECURITIES","STAMPS","Payment_Service","MAILS","Kafka","CONFIGURATIONS","CONTROLLERS","DATABASES","ENTITIES","DTOS","ENUMS"})
@EnableDiscoveryClient
public class AnUnpublishedProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnUnpublishedProjectApplication.class, args);
	}

}
