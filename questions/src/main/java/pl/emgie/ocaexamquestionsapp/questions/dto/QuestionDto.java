package pl.emgie.ocaexamquestionsapp.questions.dto;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QuestionDto {

    private BigInteger id;

    @NotNull
    private String question;

    private Set<TagDto> tags;

    @NotNull
    private String answerId;

    @NotNull
    private List<AnswerDto> answers = new ArrayList<>();

    private List<BigInteger> attachments = new ArrayList<>();

    private String description;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<TagDto> getTags() {
        return tags;
    }

    public void setTags(Set<TagDto> tags) {
        this.tags = tags;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }

    public List<BigInteger> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<BigInteger> attachments) {
        this.attachments = attachments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
