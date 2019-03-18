package pl.emgie.ocaexamquestionsapp.questions.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="answers")
@Getter
@Setter
class AnswerEntity {

    @Id
    @GeneratedValue(generator = "answer_generator")
    @SequenceGenerator(name = "answer_generator", sequenceName = "answer_seq", allocationSize = 1)
    private BigInteger id;
    private String answerNumber;
    private String answer;


}
