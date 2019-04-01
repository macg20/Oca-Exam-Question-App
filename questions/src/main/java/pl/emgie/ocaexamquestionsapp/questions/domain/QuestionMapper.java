package pl.emgie.ocaexamquestionsapp.questions.domain;

import org.springframework.stereotype.Component;
import pl.emgie.ocaexamquestionsapp.questions.dto.AnswerDto;
import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionDto;
import pl.emgie.ocaexamquestionsapp.questions.dto.TagDto;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class QuestionMapper extends AbstractMapper<QuestionEntity, QuestionDto> {

    private TagMapper tagMapper;
    private AnswerMapper answerMapper;
    private QuestionAttachmentIdMapper questionAttachmentIdMapper;
    private TagRepository tagRepository;

    public QuestionMapper(TagMapper tagMapper, AnswerMapper answerMapper, QuestionAttachmentIdMapper questionAttachmentIdMapper,
                          TagRepository tagRepository) {
        this.tagMapper = tagMapper;
        this.answerMapper = answerMapper;
        this.questionAttachmentIdMapper = questionAttachmentIdMapper;
        this.tagRepository = tagRepository;
    }

    @Override
    public QuestionDto toDto(QuestionEntity entity) {
        QuestionDto dto = new QuestionDto();
        dto.setId(entity.getId());
        dto.setAnswerId(entity.getAnswerId());
        dto.setTags(toQuestionAttachmentIdDto(entity.getTags()));
        dto.setDescription(entity.getDescription());
        dto.setAnswers(toAnswerDto(entity.getAnswers()));
        dto.setAttachments(toQuestionAttachmentIdDto(entity.getQuestionAttachments()));
        return dto;
    }

    @Override
    public QuestionEntity toEntity(QuestionDto dto) {
        QuestionEntity question = new QuestionEntity();
        question.setQuestion(dto.getQuestion());
        question.setAnswerId(dto.getAnswerId());
        question.setAnswers(dto.getAnswers().stream().map(answerMapper::toEntity).collect(Collectors.toList()));
        question.setTags(toEntity(dto.getTags()));
        question.setDescription(dto.getDescription());
        question.setQuestionAttachments(dto.getAttachments().stream().map(questionAttachmentIdMapper::toEntity).collect(Collectors.toList()));
        return question;
    }

    private Set<TagEntity> toEntity(Set<TagDto> tagDtos) {
        Set<TagEntity> tags = new HashSet<>();
        for (TagDto tagDto : tagDtos) {
            if (tagDto.getId() != null) {
                TagEntity tag = tagRepository.findById(tagDto.getId()).get();
                tags.add(tag);
            } else {
                TagEntity tag = tagMapper.toEntity(tagDto);
                tags.add(tag);
            }
        }
        return tags;
    }
    private Set<TagDto> toQuestionAttachmentIdDto(Set<TagEntity> tags) {
        return tags != null ? tags.stream().map(tagMapper::toDto).collect(Collectors.toSet()) : Collections.emptySet();
    }

    private List<AnswerDto> toAnswerDto(List<AnswerEntity> entity) {
        return entity.stream().map(answerMapper::toDto).collect(Collectors.toList());
    }

    private List<BigInteger> toQuestionAttachmentIdDto(List<QuestionAttachmentId> questionAttachmentIds) {
        List<BigInteger> dtos = Collections.emptyList();
        if (Optional.ofNullable(questionAttachmentIds).isPresent())
            return questionAttachmentIds.stream().map(questionAttachmentIdMapper::toDto).collect(Collectors.toList());
        return dtos;
    }
}
