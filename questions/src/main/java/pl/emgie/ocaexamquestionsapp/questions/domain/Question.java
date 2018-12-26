package pl.emgie.ocaexamquestionsapp.questions.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

import static org.springframework.data.elasticsearch.annotations.FieldType.*;

@Document(indexName = "questions", type = "question")
class Question {

    @Id
    private String id;

    @Field(type = Keyword)
    private String question;

    @Field(type = Keyword)
    private String[] tags;

    @Field(type = Text)
    private String answerId;

    @Field(type = Nested, includeInParent = true)
    private List<Answer> answers;

    @Field(type = Text)
    private List<Attachment> attachments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<pl.emgie.ocaexamquestionsapp.questions.domain.Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<pl.emgie.ocaexamquestionsapp.questions.domain.Attachment> attachments) {
        this.attachments = attachments;
    }
}
