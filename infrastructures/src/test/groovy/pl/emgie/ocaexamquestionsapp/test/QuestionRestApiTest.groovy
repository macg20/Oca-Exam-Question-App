package pl.emgie.ocaexamquestionsapp.test

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import pl.emgie.ocaexamquestionsapp.questions.QuestionService
import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionDto
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static pl.emgie.ocaexamquestionsapp.test.data.DummyTestData.*

@WebMvcTest
class QuestionRestApiTest extends Specification {


    @Autowired
    MockMvc mvc

    @Autowired
    QuestionService questionService

    @Autowired
    ObjectMapper objectMapper

    def saveTest() {
        given:
        QuestionDto request = prepareData()

        and:
        questionService.save(request) >> preapreDataWithId()

        when:
        def result = mvc.perform(post('/question/').contentType(MediaType.APPLICATION_JSON).content(toJson(request)))

        then:
        result.andExpect(status().isOk())

    }

    def deleteTest() {
        given:
        String id = QUESTION_ID

        and:
        questionService.delete(id) >> {}

        when:
        def result = mvc.perform(delete('/question/del/').param("questionId", id))

        then:
        result.andExpect(status().isOk())

    }

    String toJson(QuestionDto questionDto) {
        Gson gson = new Gson()
        return gson.toJson(questionDto, QuestionDto.class)
    }

    def findAllTest() {
        given:
        Integer page = 1
        Integer size = 5

        and:

        questionService.findAllQuestions(PageRequest.of(1, 5)) >> pageResponse()

        when:
        def result = mvc.perform(get('/question/').param("page", page.toString()).param("size", size.toString()))

        then:
        result.andExpect(status().isOk())
        result.andExpect(content().contentType("application/json;charset=UTF-8"))
        result.andDo(print())

    }

    @TestConfiguration
    static class StubConfig {
        DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

        @Bean
        QuestionService testQuestionService() {
            return detachedMockFactory.Stub(QuestionService)
        }

    }

}
