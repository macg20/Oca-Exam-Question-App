package pl.emgie.ocaexamquestionsapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import pl.emgie.ocaexamquestionsapp.exceptions.ResourceNotFoundException;
import pl.emgie.ocaexamquestionsapp.questions.QuestionService;
import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionDto;

import java.util.List;
import java.util.Optional;

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
    List<QuestionDto> findAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<QuestionDto> result = questionService.findAllQuestions(PageRequest.of(page, size));
        return Optional.ofNullable(result.getContent()).orElseThrow(()-> new ResourceNotFoundException());
    }
}
