package pl.emgie.ocaexamquestionsapp.questions.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.emgie.ocaexamquestionsapp.questions.domain.fegin.AtachmentServiceProxy;
import pl.emgie.ocaexamquestionsapp.questions.dto.AnswerDto;
import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionDto;
import pl.emgie.ocaexamquestionsapp.questions.dto.TagDto;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
class QuestionServiceImpl implements QuestionService {

    private AtachmentServiceProxy atachmentServiceProxy;
    private QuestionRepository questionRepository;
    private TagRepository tagRepository;

    @Autowired
    public QuestionServiceImpl(AtachmentServiceProxy atachmentServiceProxy, QuestionRepository questionRepository,
                               TagRepository tagRepository) {
        this.atachmentServiceProxy = atachmentServiceProxy;
        this.questionRepository = questionRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QuestionDto> findAllQuestions(Pageable pageable) {
        Page<QuestionEntity> questionPage = questionRepository.findAll(pageable);
        return toPageDto(questionPage, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QuestionDto> findByTag(String tag, Pageable pageable) {
        throw new RuntimeException();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QuestionDto> findByQuestionWord(Pageable pageable) {
        throw new RuntimeException();
    }

    @Override
    @Transactional
    public QuestionDto save(QuestionDto dto) {
        if (dto.getId() != null) {
            QuestionEntity fromDb = questionRepository.findById(dto.getId()).get();
            fromDb = assignAttributes(fromDb, dto);
            questionRepository.save(fromDb);
            return dto;
        }
        QuestionEntity question = toEntity(dto);
        questionRepository.save(question);
        return toDto(question);
    }

    @Override
    @Transactional
    public void delete(BigInteger questionId) {
        Optional<QuestionEntity> question = questionRepository.findById(questionId);
        question.ifPresent(q -> deleteAttachments(q.getQuestionAttachments()));
        question.ifPresent(questionRepository::delete);
    }

    private QuestionEntity assignAttributes(QuestionEntity fromDb, QuestionDto dto) {
        QuestionEntity question = toEntity(dto);
        return assignAttributes(question, fromDb);
    }

    private QuestionEntity assignAttributes(QuestionEntity question, QuestionEntity fromDb) {
        QuestionEntity entity = fromDb;
        entity.setQuestion(question.getQuestion());
        entity.setAnswerId(question.getAnswerId());
        entity.setTags(question.getTags());
        entity.setAnswers(question.getAnswers());
        entity.setDescription(question.getDescription());
        entity.setQuestionAttachments(question.getQuestionAttachments());
        return entity;
    }

    private Set<TagEntity> toEntity(Set<TagDto> tagDtos) {
        Set<TagEntity>  tags = new HashSet<>();
        for (TagDto tagDto : tagDtos) {
            if (tagDto.getId() != null) {
                TagEntity tag = tagRepository.findById(tagDto.getId()).get();
                tags.add(tag);
            } else {
                TagEntity tag = toDto(tagDto);
                tags.add(tag);
            }
        }
        return tags;
    }

    private TagEntity toDto(TagDto tagDto) {
        TagEntity entity = new TagEntity();
        entity.setName(tagDto.getName());
        return entity;
    }

    private QuestionDto toDto(QuestionEntity question) {
        QuestionDto dto = new QuestionDto();
        dto.setId(question.getId());
        dto.setAnswerId(question.getAnswerId());
        dto.setTags(toDto(question.getTags()));
        dto.setDescription(question.getDescription());
        dto.setAnswers(question.getAnswers().stream().map(this::toDto).collect(Collectors.toList()));
        dto.setAttachments(toDto(question.getQuestionAttachments()));
        return dto;
    }

    private void deleteAttachments(List<QuestionAttachmentId> questionAttachments) {
        questionAttachments.forEach(e->atachmentServiceProxy.deleteAttachment(e.getAttachment()));
    }

    private List<BigInteger> toDto(List<QuestionAttachmentId> questionAttachmentIds) {
        List<BigInteger> dtos = Collections.emptyList();
        if (Optional.ofNullable(questionAttachmentIds).isPresent())
            return questionAttachmentIds.stream().map(e -> e.getAttachment()).collect(Collectors.toList());
        return dtos;
    }

    private AnswerDto toDto(AnswerEntity answer) {
        AnswerDto dto = new AnswerDto();
        dto.setId(answer.getId());
        dto.setAnswerNumber(answer.getAnswerNumber());
        dto.setAnswer(answer.getAnswer());
        return dto;
    }

    private QuestionEntity toEntity(QuestionDto dto) {
        QuestionEntity question = new QuestionEntity();
        question.setQuestion(dto.getQuestion());
        question.setAnswerId(dto.getAnswerId());
        question.setAnswers(dto.getAnswers().stream().map(this::toEntity).collect(Collectors.toList()));
        question.setTags(toEntity(dto.getTags()));
        question.setDescription(dto.getDescription());
        question.setQuestionAttachments(dto.getAttachments().stream().map(this::toEntity).collect(Collectors.toList()));
        return question;
    }

    private AnswerEntity toEntity(AnswerDto dto) {
        AnswerEntity answer = new AnswerEntity();
        answer.setAnswer(dto.getAnswer());
        answer.setAnswerNumber(dto.getAnswerNumber());
        return answer;
    }

    private TagEntity toEntity(String dto) {
        TagEntity tag = new TagEntity();
        tag.setName(dto.toUpperCase());
        return tag;
    }

    private QuestionAttachmentId toEntity(BigInteger dto) {
        return new QuestionAttachmentId(dto);
    }

    private Page<QuestionDto> toPageDto(Page<QuestionEntity> source, Pageable pageable) {
        List<QuestionDto> dtos = source.getContent().stream().map(this::toDto).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, source.getTotalElements());
    }

    private Set<TagDto> toDto(Set<TagEntity> tags) {
        return tags != null ? tags.stream().map(this::toDto).collect(Collectors.toSet()) : Collections.emptySet();
    }

    private TagDto toDto(TagEntity tagEntity) {
        TagDto dto = new TagDto();
        dto.setId(tagEntity.getId());
        dto.setName(tagEntity.getName());
        return dto;
    }
}
