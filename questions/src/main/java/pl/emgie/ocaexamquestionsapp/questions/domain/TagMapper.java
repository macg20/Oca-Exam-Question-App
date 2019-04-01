package pl.emgie.ocaexamquestionsapp.questions.domain;

import org.springframework.stereotype.Component;
import pl.emgie.ocaexamquestionsapp.questions.dto.TagDto;

@Component
public class TagMapper extends AbstractMapper<TagEntity, TagDto> {
    @Override
    public TagDto toDto(TagEntity entity) {
        TagDto dto = new TagDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    @Override
    public TagEntity toEntity(TagDto dto) {
        TagEntity entity = new TagEntity();
        entity.setName(dto.getName());
        return entity;
    }
}
