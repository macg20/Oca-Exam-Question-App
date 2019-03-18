package pl.emgie.ocaexamquestionsapp.questions.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.math.BigInteger;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAttachmentId {

    private BigInteger attachment;
}
