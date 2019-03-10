package pl.emgie.ocaexamquestionsapp.questions.dto;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public class AnswerDto {

    private BigInteger id;
    @NotNull
    private String answer;
    @NotNull
    private String answerNumber;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(String answerNumber) {
        this.answerNumber = answerNumber;
    }
}
