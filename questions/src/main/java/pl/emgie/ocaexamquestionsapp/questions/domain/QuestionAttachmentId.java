package pl.emgie.ocaexamquestionsapp.questions.domain;

import javax.persistence.Embeddable;
import java.math.BigInteger;

@Embeddable
public class QuestionAttachmentId {

    private BigInteger attachment;

    public QuestionAttachmentId(BigInteger attachment) {
        this.attachment = attachment;
    }

    public BigInteger getAttachment() {
        return attachment;
    }

    public void setAttachment(BigInteger attachment) {
        this.attachment = attachment;
    }
}
