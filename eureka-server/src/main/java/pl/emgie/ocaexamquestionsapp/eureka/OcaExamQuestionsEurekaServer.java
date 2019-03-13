package pl.emgie.ocaexamquestionsapp.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "pl.emgie.ocaexamquestionsapp.")
@EnableEurekaServer
public class OcaExamQuestionsEurekaServer {

    public static void main(String[] args) {
        SpringApplication.run(OcaExamQuestionsEurekaServer.class, args);

    }
}

