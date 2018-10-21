package pl.emgie.ocaexamquestionsapp.questions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionDto;

public interface QuestionService extends BaseService{

    Page<QuestionDto> findAllQuestions(Pageable pageable);

    Page<QuestionDto> findByTag(String tag,Pageable pageable);

    Page<QuestionDto> findByQuestionWord(Pageable pageable);

    void save(QuestionDto dto);
}
