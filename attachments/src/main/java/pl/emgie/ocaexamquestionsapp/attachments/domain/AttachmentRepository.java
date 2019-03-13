package pl.emgie.ocaexamquestionsapp.attachments.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

interface AttachmentRepository extends JpaRepository<AttachmentEntity, BigInteger> {

    String findPathById(BigInteger id);
}
