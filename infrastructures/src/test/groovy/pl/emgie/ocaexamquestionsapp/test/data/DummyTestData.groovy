package pl.emgie.ocaexamquestionsapp.test.data

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import pl.emgie.ocaexamquestionsapp.questions.Question
import pl.emgie.ocaexamquestionsapp.questions.dto.AnswerDto
import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionDto

class DummyTestData {

    static final String QUESTION_ID = "sadsadweqwqeqwewereregfgdsg"

    static QuestionDto prepareData() {
        QuestionDto dto = prepareSimpleData()
        return dto
    }

    static QuestionDto preapreDataWithId() {
        QuestionDto dto = prepareSimpleData()
        dto.setId(QUESTION_ID)
        return dto
    }

    private static QuestionDto prepareSimpleData() {

        QuestionDto dto = new QuestionDto()
        dto.setAnswerId("A")
        dto.setQuestion("Simple question")
        dto.setAnswers(answersDto())
        return dto
    }

    static List<QuestionDto> questionDtos() {

        QuestionDto dto1 = new QuestionDto()
        dto1.setId("dasdasdas+1")
        dto1.setAnswerId("A")
        dto1.setQuestion("First question -1")

        QuestionDto dto2 = new QuestionDto()
        dto2.setId("dasdasdas+2")
        dto2.setAnswerId("B")
        dto2.setQuestion("Second question - 2")

        QuestionDto dto3 = new QuestionDto()
        dto3.setId("dasdasdas+3")
        dto3.setAnswerId("A")
        dto3.setQuestion("Third question - 3")

        return List.of(dto1, dto2, dto3)
    }

    static Page<QuestionDto> pageResponse() {
        def dtos = questionDtos()
        return new PageImpl<>(dtos, PageRequest.of(1, 5), dtos.size())
    }

    private static List<AnswerDto> answersDto() {
        AnswerDto answerDto1 = new AnswerDto()
        answerDto1.setAnswerId("A")
        answerDto1.setAnswer("Yes")

        AnswerDto answerDto2 = new AnswerDto()
        answerDto2.setAnswerId("B")
        answerDto2.setAnswer("No")

        return List.of(answerDto1, answerDto2)
    }
}
