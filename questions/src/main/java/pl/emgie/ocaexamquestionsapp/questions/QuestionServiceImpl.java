package pl.emgie.ocaexamquestionsapp.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.emgie.ocaexamquestionsapp.questions.dto.AnswerDto;
import pl.emgie.ocaexamquestionsapp.questions.dto.AttachmentDto;
import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionDto;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private AttachmentService attachmentService;
    private QuestionRepository repository;

//    @Autowired
//    public QuestionServiceImpl(AttachmentService attachmentService, QuestionCustomRepositoryImpl repository) {
//        this.attachmentService = attachmentService;
//        this.repository = repository;
//    }

    @Autowired
    public QuestionServiceImpl(AttachmentService attachmentService, QuestionRepository repository) {
        this.attachmentService = attachmentService;
        this.repository = repository;
    }

    @Override
    public Page<QuestionDto> findAllQuestions(Pageable pageable) {
        Page<Question> questionPage = repository.findAll(pageable);
        return null;
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
    public void save(QuestionDto dto) {
        Question question = toDocument(dto);
        repository.save(question);
    }

    private QuestionDto toDto(Question question) {
        QuestionDto dto = new QuestionDto();
        dto.setId(question.getId());
        dto.setAnswerId(question.getAnswerId());
        dto.setTags(question.getTags());
        dto.setAnswers(question.getAnswers().stream().map(this::toDto).collect(Collectors.toList()));
        dto.setAttachments(readAttachments(question.getAttachments()));
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
        question.setAttachments(insertAttachments(dto.getAttachments()));
        return question;
    }

    private Answer toDocument(AnswerDto dto) {
        Answer answer = new Answer();
        answer.setAnswer(dto.getAnswer());
        answer.setAnswerId(dto.getAnswerId());
        return answer;
    }

    private List<Attachment> insertAttachments(List<AttachmentDto> dtos) {
        if (dtos != null) {
            List<Attachment> attachments = new ArrayList<>();
            dtos.forEach(dto -> {
                Path attachmentPath = attachmentService.insert(dto.getContent());
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
                dtos.add(attachmentDto);
            });
            return dtos;
        }
        return Collections.<AttachmentDto>emptyList();

    }

    private Page<QuestionDto> toPageDto(Page<Question> source, Pageable pageable) {
        List<QuestionDto> dtos = source.getContent().stream().map(this::toDto).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, source.getTotalElements());
    }


}