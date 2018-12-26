package pl.emgie.ocaexamquestionsapp.questions.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Repository
public class QuestionCustomRepositoryImpl implements QuestionCustomRepository {

    @Autowired
    private ElasticsearchOperations template;


    public Page<Question> findByTag(String tag, Pageable pageable) {
        SearchQuery searchQuery = (new NativeSearchQueryBuilder())
                .withQuery(matchQuery("tags", tag))
                .withPageable(pageable)
                .build();
        return template.queryForPage(searchQuery, Question.class);
    }

    @Override
    public Page<Question> findAll(Pageable pageable) {
        SearchQuery searchQuery = (new NativeSearchQueryBuilder())
                .withQuery(matchAllQuery())
                .withPageable(pageable).build();
        return null;
    }

}
