package pl.emgie.ocaexamquestionsapp.questions.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Getter
@Setter
public class AnswerDto {

    private BigInteger id;
    @NotNull
    private String answer;
    @NotNull
    private String answerNumber;
}
