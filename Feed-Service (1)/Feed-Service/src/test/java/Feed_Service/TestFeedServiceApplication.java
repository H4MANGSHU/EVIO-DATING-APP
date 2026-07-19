package Feed_Service;

import org.springframework.boot.SpringApplication;

public class TestFeedServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(FeedServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
