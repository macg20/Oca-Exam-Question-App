package pl.emgie.ocaexamquestionsapp.attachments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication(scanBasePackages = "pl.emgie.ocaexamquestionsapp")
@EnableEurekaClient
public class OcaExamQuestionAppAttachments {

    public static void main(String[] args) {
        SpringApplication.run(OcaExamQuestionAppAttachments.class, args);
    }
}
