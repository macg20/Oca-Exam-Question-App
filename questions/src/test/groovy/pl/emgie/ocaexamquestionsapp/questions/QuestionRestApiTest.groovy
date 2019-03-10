package pl.emgie.ocaexamquestionsapp.questions

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration
import org.springframework.cloud.openfeign.FeignAutoConfiguration
import org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import pl.emgie.ocaexamquestionsapp.questions.domain.QuestionService
import pl.emgie.ocaexamquestionsapp.questions.domain.fegin.AtachmentServiceProxy
import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionDto
import spock.lang.Specification
import spock.mock.DetachedMockFactory
import org.springframework.boot.autoconfigure.ImportAutoConfiguration

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static pl.emgie.ocaexamquestionsapp.questions.data.DummyTestData.*


@WebMvcTest
@ImportAutoConfiguration(classes =[RibbonAutoConfiguration , FeignRibbonClientAutoConfiguration , FeignAutoConfiguration])
class QuestionRestApiTest extends Specification {


    @Autowired
    MockMvc mvc

    @Autowired
    QuestionService questionService

    @Autowired
    AtachmentServiceProxy atachmentServiceProxy

    @Autowired
    ObjectMapper objectMapper

    def saveTest() {
        given:
        QuestionDto request = prepareData()

        and:
        atachmentServiceProxy.insertAttachment(_) >> "DummyString"
        questionService.save(request) >> preapreDataWithId()

        when:
        def result = mvc.perform(post('/questions/').contentType(MediaType.APPLICATION_JSON).content(toJson(request)))

        then:
        result.andExpect(status().isOk())

    }

    def deleteTest() {
        given:
        BigInteger id = questionId()

        and:
        questionService.delete(id) >> {}

        when:
        def result = mvc.perform(delete('/questions/del/').param("questionId", id.toString()))

        then:
        result.andExpect(status().isOk())

    }

    String toJson(QuestionDto questionDto) {

        Gson gson = new Gson()
        print(gson.toJson(questionDto, QuestionDto.class))
        return gson.toJson(questionDto, QuestionDto.class)
    }

    def findAllTest() {
        given:
        Integer page = 1
        Integer size = 5

        and:

        questionService.findAllQuestions(PageRequest.of(1, 5)) >> pageResponse()

        when:
        def result = mvc.perform(get('/questions/').param("page", page.toString()).param("size", size.toString()))

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

        @Bean
        AtachmentServiceProxy testAttachmentServiceProxy() {
            detachedMockFactory.Stub(AtachmentServiceProxy)
        }

    }

}
