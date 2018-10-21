package pl.emgie.ocaexamquestionsapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.emgie.ocaexamquestionsapp.questions.QuestionRepository;

@SpringBootApplication
public class OcaExamQuestionsApp implements CommandLineRunner {

    @Autowired
    QuestionRepository questionRepository;

    public static void main(String[] args) {
        SpringApplication.run(OcaExamQuestionsApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        questionRepository.findAll();
    }
}
