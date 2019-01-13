package pl.emgie.ocaexamquestionsapp.questions.infrastructure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.emgie.ocaexamquestionsapp.commons.exceptions.InvalidParameterException;
import pl.emgie.ocaexamquestionsapp.questions.domain.QuestionService;
import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionDto;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/questions")
class QuestionRestService {

    private QuestionService questionService;

    @Autowired
    public QuestionRestService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(value = "/", params = {"page", "size"})
    @ResponseBody
    Page<QuestionDto> findAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<QuestionDto> result = questionService
                .findAllQuestions(PageRequest.of(
                        Optional.ofNullable(page).orElseThrow(() -> new InvalidParameterException("Page cannot be null and grather than zero!")),
                        Optional.ofNullable(size).orElseThrow(() -> new InvalidParameterException("Page size cannot be null and grather than zero!"))));
        return result;
    }

    @DeleteMapping(value = "/del/", params = {"questionId"})
    ResponseEntity<?> delete(@Param("questionId") String questionId) {
        questionService.delete(questionId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/")
    ResponseEntity<?> save(@RequestBody @Valid QuestionDto dto) {
        questionService.save(dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
