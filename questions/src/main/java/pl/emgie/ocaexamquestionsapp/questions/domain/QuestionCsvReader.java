package pl.emgie.ocaexamquestionsapp.questions.domain;

import pl.emgie.ocaexamquestionsapp.questions.dto.QuestionCsvFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class QuestionCsvReader implements BaseService {

    public static List<String[]> readCsv(QuestionCsvFile csvFile) {
        String csvFileAsString = convertByteArrayToString(csvFile.getContent());
        return splitCsvAsSeperator(csvFileAsString, csvFile.getSeparator());
    }

    private static String convertByteArrayToString(byte[] array) {
        return new String(array);
    }

    private static List<String[]> splitCsvAsSeperator(String file, String separator) {

        return Stream.of(file)
                .map(e -> e.split("\n"))
                .map(Arrays::stream)
                .flatMap(e -> e.map(i -> i.split(separator)))
                .collect(Collectors.toList());
    }

}
