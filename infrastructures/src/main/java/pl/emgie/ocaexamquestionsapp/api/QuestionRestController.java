package pl.emgie.ocaexamquestionsapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.emgie.ocaexamquestionsapp.questions.QuestionService;
import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionDto;

@RestController
@RequestMapping(value = "/question")
class QuestionRestController {

    private QuestionService questionService;

    @Autowired
    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(value = "/", params = {"page", "size"})
    @ResponseBody
    Page<QuestionDto> findAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<QuestionDto> result = questionService.findAllQuestions(PageRequest.of(page, size));
        return result;
    }

    @DeleteMapping(value="/del/", params = {"questionId"})
    ResponseEntity<?> delete(@Param("questionId") String questionId ) {
        questionService.delete(questionId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/")
    ResponseEntity<?> save(@RequestBody QuestionDto dto) {
        questionService.save(dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
