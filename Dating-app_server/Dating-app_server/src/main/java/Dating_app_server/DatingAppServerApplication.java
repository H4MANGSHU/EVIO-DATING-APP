package Dating_app_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DatingAppServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatingAppServerApplication.class, args);
	}

}
