package pl.emgie.ocaexamquestionsapp.questions.dto;

import javax.validation.constraints.NotNull;

public class AnswerDto {

    @NotNull
    private String answerId;
    @NotNull
    private String answer;

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
