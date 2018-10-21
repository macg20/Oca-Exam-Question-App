package pl.emgie.ocaexamquestionsapp.questions;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

public interface QuestionRepository extends ElasticsearchRepository<Question,String>, QuestionCustomRepository {
}
