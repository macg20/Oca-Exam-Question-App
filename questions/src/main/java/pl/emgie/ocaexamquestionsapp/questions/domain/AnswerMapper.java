package pl.emgie.ocaexamquestionsapp.questions.domain;

import org.springframework.stereotype.Component;
import pl.emgie.ocaexamquestionsapp.questions.dto.AnswerDto;

@Component
public class AnswerMapper extends AbstractMapper<AnswerEntity, AnswerDto> {
    @Override
    public AnswerDto toDto(AnswerEntity entity) {
        AnswerDto dto = new AnswerDto();
        dto.setId(entity.getId());
        dto.setAnswerNumber(entity.getAnswerNumber());
        dto.setAnswer(entity.getAnswer());
        return dto;
    }

    @Override
    public AnswerEntity toEntity(AnswerDto dto) {
        AnswerEntity answer = new AnswerEntity();
        answer.setAnswer(dto.getAnswer());
        answer.setAnswerNumber(dto.getAnswerNumber());
        return answer;
    }
}
