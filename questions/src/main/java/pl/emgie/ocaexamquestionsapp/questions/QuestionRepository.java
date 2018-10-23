package pl.emgie.ocaexamquestionsapp.questions;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface QuestionRepository extends ElasticsearchRepository<Question, String>, QuestionCustomRepository {
}
