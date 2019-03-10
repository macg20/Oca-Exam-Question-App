package pl.emgie.ocaexamquestionsapp.questions.domain;


import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="answers")
class AnswerEntity {

    @Id
    @GeneratedValue(generator = "answer_generator")
    @SequenceGenerator(name = "answer_generator", sequenceName = "answer_seq", allocationSize = 1)
    private BigInteger id;
    private String answerNumber;
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public BigInteger getId() {
        return id;
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
