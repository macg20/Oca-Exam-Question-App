package pl.emgie.ocaexamquestionsapp.questions.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionCsvFile;
import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionDto;

import java.math.BigInteger;
import java.util.List;

public interface QuestionService extends BaseService {

    Page<QuestionDto> findAllQuestions(Pageable pageable);

    Page<QuestionDto> findByTag(String tag, Pageable pageable);

    Page<QuestionDto> findByQuestionWord(Pageable pageable);

    QuestionDto save(QuestionDto dto);

    void delete(BigInteger questionId);

    void csvImportQuestions(List<QuestionCsvFile> csvFiles);
}
