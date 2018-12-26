package pl.emgie.ocaexamquestionsapp.questions.domain;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface QuestionRepository extends ElasticsearchRepository<Question, String>, QuestionCustomRepository {
}
