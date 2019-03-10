package pl.emgie.ocaexamquestionsapp.questions.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

interface TagRepository extends JpaRepository<TagEntity, BigInteger> {
}
