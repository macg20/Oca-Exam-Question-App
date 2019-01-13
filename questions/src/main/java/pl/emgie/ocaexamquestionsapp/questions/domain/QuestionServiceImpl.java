package pl.emgie.ocaexamquestionsapp.questions.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.emgie.ocaexamquestionsapp.questions.domain.fegin.AtachmentServiceProxy;
import pl.emgie.ocaexamquestionsapp.questions.dto.AnswerDto;
import pl.emgie.ocaexamquestionsapp.questions.dto.AttachmentDto;
import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private AtachmentServiceProxy atachmentServiceProxy;
    private QuestionRepository repository;
    private AttachmentService attachmentService;

    @Autowired
    public QuestionServiceImpl(AtachmentServiceProxy atachmentServiceProxy, QuestionRepository repository, AttachmentService attachmentService) {
        this.atachmentServiceProxy = atachmentServiceProxy;
        this.repository = repository;
        this.attachmentService = attachmentService;
    }

//    @Autowired
//    public QuestionServiceImpl(AtachmentServiceProxy atachmentServiceProxy, QuestionRepository repository) {
//        this.atachmentServiceProxy = atachmentServiceProxy;
//        this.repository = repository;
//    }

    @Override
    public Page<QuestionDto> findAllQuestions(Pageable pageable) {
        Page<Question> questionPage = repository.findAll(pageable);
        return toPageDto(questionPage,pageable);
    }

    @Override
    public Page<QuestionDto> findByTag(String tag, Pageable pageable) {
        Page<Question> questionPage = repository.findByTag(tag, pageable);
        return toPageDto(questionPage, pageable);
    }

    @Override
    public Page<QuestionDto> findByQuestionWord(Pageable pageable) {
        return null;
    }

    @Override
    public QuestionDto save(QuestionDto dto) {
        Question question = toDocument(dto);
        insertAttachments(dto.getAttachments());
        question = repository.save(question);
        return  toDto(question);
    }

    @Override
    public void delete(String questionId) {
       Optional<Question> question = repository.findById(questionId);
//       question.ifPresent(q-> deleteAttachments(q.getAttachments()));
       question.ifPresent(repository::delete);
    }

    private QuestionDto toDto(Question question) {
        QuestionDto dto = new QuestionDto();
        dto.setId(question.getId());
        dto.setAnswerId(question.getAnswerId());
        dto.setTags(question.getTags());
        dto.setDescription(question.getDescription());
        dto.setAnswers(question.getAnswers().stream().map(this::toDto).collect(Collectors.toList()));
//        dto.setAttachments(readAttachments(question.getAttachments()));
        return dto;
    }

    private AnswerDto toDto(Answer answer) {
        AnswerDto dto = new AnswerDto();
        answer.setAnswer(answer.getAnswer());
        return dto;
    }

    private Question toDocument(QuestionDto dto) {
        Question question = new Question();
        question.setQuestion(dto.getQuestion());
        question.setAnswerId(dto.getAnswerId());
        question.setAnswers(dto.getAnswers().stream().map(this::toDocument).collect(Collectors.toList()));
        question.setTags(dto.getTags());
        question.setDescription(dto.getDescription());
//        question.setAttachments(insertAttachments(dto.getAttachments()));
        return question;
    }

    private Answer toDocument(AnswerDto dto) {
        Answer answer = new Answer();
        answer.setAnswer(dto.getAnswer());
        answer.setId(dto.getId());
        return answer;
    }

    private List<Attachment> insertAttachments(List<AttachmentDto> dtos) {
        if (dtos != null) {
            List<Attachment> attachments = new ArrayList<>();
            dtos.forEach(dto -> {
                String attachmentPath = atachmentServiceProxy.insertAttachment(dto);
                Attachment attachment = new Attachment();
                attachment.setName(dto.getName());
                attachment.setPath(attachmentPath.toString());
                attachments.add(attachment);
            });
            return attachments;
        }

        return Collections.<Attachment>emptyList();
    }

    private List<AttachmentDto> readAttachments(List<Attachment> attachments) {
        if (attachments != null) {
            List<AttachmentDto> dtos = new ArrayList<>();
            attachments.forEach(attachment -> {
                AttachmentDto attachmentDto = attachmentService.read(attachment.getPath());
                attachment.setName(attachment.getName());
                dtos.add(attachmentDto);
            });
            return dtos;
        }
        return Collections.emptyList();

    }

    private Page<QuestionDto> toPageDto(Page<Question> source, Pageable pageable) {
        List<QuestionDto> dtos = source.getContent().stream().map(this::toDto).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, source.getTotalElements());
    }

//    private void deleteAttachments(List<Attachment> attachments) {
//        attachments.forEach(e-> attachmentService.delete(e.getPath()));
//    }
}
