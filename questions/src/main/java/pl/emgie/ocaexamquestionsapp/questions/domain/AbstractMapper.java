package pl.emgie.ocaexamquestionsapp.questions.domain;

abstract class AbstractMapper<E, T> {

    public abstract T toDto(E entity);

    public abstract E toEntity(T dto);
}
