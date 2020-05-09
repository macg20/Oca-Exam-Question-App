package pl.emgie.ocaexamquestionsapp.questions


import org.springframework.test.context.ActiveProfiles
import pl.emgie.ocaexamquestionsapp.questions.domain.QuestionCsvReader
import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionCsvFile
import spock.lang.Specification

@ActiveProfiles("test")
class QuestionCsvServiceTest extends Specification {


    def readCsvTest() {
        given:

        String file = dummyCsvFile()
        byte[] bytes = file.getBytes()
        QuestionCsvFile csv = new QuestionCsvFile()
        csv.setContent(bytes)
        csv.setSeparator(";")

        when:
        List<String[]> result = QuestionCsvReader.readCsv(csv)

        then:
        result.size() == 2
    }


    def dummyCsvFile() {
        String line1 = "question1;answer1;answer2;C"
        String line2 = "question1;answer1;answer2;answer3;answer4;C"
        StringBuilder stringBuilder = new StringBuilder()
        stringBuilder.append(line1)
        stringBuilder.append("\n")
        stringBuilder.append(line2)
        return stringBuilder.toString();
    }
}
