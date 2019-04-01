package pl.emgie.ocaexamquestionsapp.questions.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.emgie.ocaexamquestionsapp.questions.domain.fegin.AtachmentServiceProxy;
import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionCsvFile;
import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionDto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class QuestionServiceImpl implements QuestionService {

    private AtachmentServiceProxy atachmentServiceProxy;
    private QuestionRepository questionRepository;
    private TagRepository tagRepository;
    private QuestionMapper questionMapper;

    public QuestionServiceImpl(AtachmentServiceProxy atachmentServiceProxy, QuestionRepository questionRepository,
                               TagRepository tagRepository, QuestionMapper questionMapper) {
        this.atachmentServiceProxy = atachmentServiceProxy;
        this.questionRepository = questionRepository;
        this.tagRepository = tagRepository;
        this.questionMapper = questionMapper;
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
            Optional<QuestionEntity> fromDb = questionRepository.findById(dto.getId());
            fromDb.ifPresent(e -> questionRepository.save(assignAttributes(e, dto)));
            return dto;
        }
        QuestionEntity question = questionMapper.toEntity(dto);
        questionRepository.save(question);
        return questionMapper.toDto(question);
    }

    @Override
    @Transactional
    public void delete(BigInteger questionId) {
        Optional<QuestionEntity> question = questionRepository.findById(questionId);
        question.ifPresent(q -> deleteAttachments(q.getQuestionAttachments()));
        question.ifPresent(questionRepository::delete);
    }

    @Override
    public void csvImportQuestions(List<QuestionCsvFile> csvFiles) {
        questionRepository.saveAll(csvFiles.
                stream()
                .map(QuestionCsvReader::readCsv)
                .flatMap(List::stream)
                .map(this::csvToEntity)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

    private QuestionEntity assignAttributes(QuestionEntity fromDb, QuestionDto dto) {
        QuestionEntity question = questionMapper.toEntity(dto);
        return assignAttributes(fromDb, question);
    }

    private QuestionEntity assignAttributes(QuestionEntity fromDb, QuestionEntity question) {
        QuestionEntity entity = fromDb;
        entity.setQuestion(question.getQuestion());
        entity.setAnswerId(question.getAnswerId());
        entity.setTags(question.getTags());
        entity.setAnswers(question.getAnswers());
        entity.setDescription(question.getDescription());
        entity.setQuestionAttachments(question.getQuestionAttachments());
        return entity;
    }

    private void deleteAttachments(List<QuestionAttachmentId> questionAttachments) {
        questionAttachments.forEach(e -> atachmentServiceProxy.deleteAttachment(e.getAttachment()));
    }

    private QuestionEntity csvToEntity(String[] csvQuestion) {
        if (csvQuestion.length < 5)
            return null;

        QuestionEntity question = new QuestionEntity();
        List<AnswerEntity> answers = new ArrayList<>();
        for (int i = 0; i < csvQuestion.length; i++) {
            fillEntity(csvQuestion, question, answers, i);
        }
        question.setAnswers(answers);
        return question;
    }

    private void fillEntity(String[] csvQuestion, QuestionEntity question, List<AnswerEntity> answers, int i) {
        switch (i) {
            case 0:
                question.setQuestion(csvQuestion[i]);
                break;
            case 1:
                question.setAnswerId(csvQuestion[i]);
                break;
            case 2:
            case 3:
            case 4:
                AnswerEntity answer = new AnswerEntity();
                answer.setAnswerNumber(mapIntAsStringAnswerNumber(i - 1));
                answer.setAnswer(csvQuestion[i]);
                answers.add(answer);
                break;
            case 5:
                question.setDescription(csvQuestion[i]);
        }
    }

    private Page<QuestionDto> toPageDto(Page<QuestionEntity> source, Pageable pageable) {
        List<QuestionDto> dtos = source.getContent().stream().map(questionMapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, source.getTotalElements());
    }

    private String mapIntAsStringAnswerNumber(int number) {
        switch (number) {
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            default:
                return "X";
        }
    }
}
