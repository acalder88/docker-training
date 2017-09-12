package container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("container")
@SpringBootApplication
public class ContainerApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(ContainerApplication.class);
    app.run(args);
  }
}
