package pl.emgie.ocaexamquestionsapp.questions.dto;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class QuestionDto {

    private String id;

    @NotNull
    private String question;

    private String[] tags;

    @NotNull
    private String answerId;

    @NotNull
    private List<AnswerDto> answers = new ArrayList<>();

    private List<AttachmentDto> attachments = new ArrayList<>();

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

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }

    public List<AttachmentDto> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentDto> attachments) {
        this.attachments = attachments;
    }
}
