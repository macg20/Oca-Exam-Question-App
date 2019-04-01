package pl.emgie.ocaexamquestionsapp.questions.domain;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class QuestionAttachmentIdMapper extends AbstractMapper<QuestionAttachmentId, BigInteger> {
    @Override
    public BigInteger toDto(QuestionAttachmentId entity) {
        return entity.getAttachment();
    }

    @Override
    public QuestionAttachmentId toEntity(BigInteger dto) {
        QuestionAttachmentId questionAttachmentId = new QuestionAttachmentId();
        questionAttachmentId.setAttachment(dto);
        return questionAttachmentId;
    }
}
