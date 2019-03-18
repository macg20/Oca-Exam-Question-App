package pl.emgie.ocaexamquestionsapp.questions.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
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
}
