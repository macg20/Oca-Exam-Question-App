package pl.emgie.ocaexamquestionsapp.questions.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionCsvFile {

    private byte[] content;
    private String separator;
}
