package pl.emgie.ocaexamquestionsapp.questions.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<QuestionEntity, String> {

    Page<QuestionEntity> findAll(Pageable pageable);

    Optional<QuestionEntity> findById(BigInteger id);
}
