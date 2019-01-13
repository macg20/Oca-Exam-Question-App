package pl.emgie.ocaexamquestionsapp.questions.dto;

import javax.validation.constraints.NotNull;

public class AnswerDto {

    @NotNull
    private String id;
    @NotNull
    private String answer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
