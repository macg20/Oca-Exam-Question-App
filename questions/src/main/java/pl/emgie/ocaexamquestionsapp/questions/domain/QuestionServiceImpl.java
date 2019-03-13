package pl.emgie.ocaexamquestionsapp.questions.domain;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.emgie.ocaexamquestionsapp.questions.domain.fegin.AtachmentServiceProxy;
import pl.emgie.ocaexamquestionsapp.questions.dto.AnswerDto;
import pl.emgie.ocaexamquestionsapp.questions.dto.AttachmentDto;
import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionDto;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private AtachmentServiceProxy atachmentServiceProxy;
    private QuestionRepository repository;

    @Autowired
    public QuestionServiceImpl(AtachmentServiceProxy atachmentServiceProxy, QuestionRepository repository
    ) {
        this.atachmentServiceProxy = atachmentServiceProxy;
        this.repository = repository;
    }

    @Override
    public Page<QuestionDto> findAllQuestions(Pageable pageable) {
        Page<QuestionEntity> questionPage = repository.findAll(pageable);
        return toPageDto(questionPage, pageable);
    }

    @Override
    public Page<QuestionDto> findByTag(String tag, Pageable pageable) {
//        Page<QuestionEntity> questionPage = repository.findByTag(tag, pageable);
//        return toPageDto(questionPage, pageable);
        throw new NotImplementedException("dsadasd");
    }

    @Override
    public Page<QuestionDto> findByQuestionWord(Pageable pageable) {
        return null;
    }

    @Override
    public QuestionDto save(QuestionDto dto) {
        QuestionEntity question = toEntity(dto);
//        insertAttachments(dto.getAttachments());
        question = repository.save(question);
        return toDto(question);
    }

    @Override
    public void delete(BigInteger questionId) {
        Optional<QuestionEntity> question = repository.findById(questionId);
        question.ifPresent(q -> deleteAttachments(q.getQuestionAttachments()));
        question.ifPresent(repository::delete);
    }

    private QuestionDto toDto(QuestionEntity question) {
        QuestionDto dto = new QuestionDto();
        dto.setId(question.getId());
        dto.setAnswerId(question.getAnswerId());
        dto.setTags(magTagsToString(question.getTags()));
        dto.setDescription(question.getDescription());
//        dto.setAnswers(question.getAnswers().stream().map(this::toDto).collect(Collectors.toList()));
//        dto.setAttachments(readAttachments(question.getAttachments()));
        return dto;
    }

//    private AnswerDto toDto(AnswerEntity answer) {
//        AnswerDto dto = new AnswerDto();
//        answer.setAnswer(answer.getAnswer());
//        return dto;
//    }

    private QuestionEntity toEntity(QuestionDto dto) {
        QuestionEntity question = new QuestionEntity();
        question.setQuestion(dto.getQuestion());
        question.setAnswerId(dto.getAnswerId());
        question.setAnswers(dto.getAnswers().stream().map(this::toEntity).collect(Collectors.toList()));

        Optional<Set<String>> tags = Optional.ofNullable(dto.getTags());
        tags.ifPresent(e-> question.setTags(e.stream().map(this::toEntity).collect(Collectors.toSet())));

        question.setDescription(dto.getDescription());
        question.setQuestionAttachments(insertAttachments(Optional.ofNullable(dto.getAttachments()).orElse(Collections.emptyList())));
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

    private List<QuestionAttachmentId> insertAttachments(List<AttachmentDto> dtos) {
        if (dtos != null) {
            List<QuestionAttachmentId> attachments = new ArrayList<>();
            dtos.forEach(dto -> {
                BigInteger attachmentId = atachmentServiceProxy.insertAttachment(dto);
                QuestionAttachmentId attachment = new QuestionAttachmentId();
                attachment.setAttachment(attachmentId);
                attachments.add(attachment);
            });
            return attachments;
        }
        return Collections.emptyList();
    }

    private List<AttachmentDto> readAttachments(List<QuestionAttachmentId> attachments) {
        if (attachments != null) {
            List<AttachmentDto> dtos = new ArrayList<>();
            attachments.forEach(attachment -> {
//                AttachmentDto attachmentDto = atachmentServiceProxy.read(attachment.getAttachment());
//                attachment.setName(attachment.getName());
//                dtos.add(attachmentDto);
            });
            return dtos;
        }
        return Collections.emptyList();

    }

    private void deleteAttachments(List<QuestionAttachmentId> questionAttachments) {
//        questionAttachments.forEach(e->atachmentServiceProxy.deleteAttachment(e.getAttachment()));
    }

    private Page<QuestionDto> toPageDto(Page<QuestionEntity> source, Pageable pageable) {
        List<QuestionDto> dtos = source.getContent().stream().map(this::toDto).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, source.getTotalElements());
    }

    private Set<String> magTagsToString(Set<TagEntity> tags) {
        return tags != null ? tags.stream().map(TagEntity::getName).collect(Collectors.toSet()) : Collections.emptySet();
    }
}
