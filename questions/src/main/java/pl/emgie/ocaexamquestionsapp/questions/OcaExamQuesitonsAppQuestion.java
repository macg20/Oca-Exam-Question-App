package pl.emgie.ocaexamquestionsapp.questions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "pl.emgie.ocaexamquestionsapp")
@EnableDiscoveryClient
@EnableFeignClients
public class OcaExamQuesitonsAppQuestion {

    public static void main(String[] args) {
        SpringApplication.run(OcaExamQuesitonsAppQuestion.class, args);
    }
}
