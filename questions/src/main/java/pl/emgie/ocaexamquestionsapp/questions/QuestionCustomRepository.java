package pl.emgie.ocaexamquestionsapp.questions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionCustomRepository {

    Page<Question> findByTag(String tag, Pageable pageable);

    Page<Question> findAll(Pageable pageable);
}
