package pl.emgie.ocaexamquestionsapp.questions.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AnswerRepository extends JpaRepository<AnswerEntity, BigInteger> {
}
