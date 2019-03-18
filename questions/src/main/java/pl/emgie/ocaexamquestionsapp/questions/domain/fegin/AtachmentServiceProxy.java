package pl.emgie.ocaexamquestionsapp.questions.domain.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.emgie.ocaexamquestionsapp.questions.dto.AttachmentDto;

import java.math.BigInteger;

@FeignClient("attachments-microservices-app")
public interface AtachmentServiceProxy {

    @PostMapping("/attachments/")
    BigInteger insertAttachment(AttachmentDto attachmentDto);

    @GetMapping("/attachments/")
    AttachmentDto readAttachments();

    @DeleteMapping("/attachments/{id}")
    String deleteAttachment(@PathVariable("id") BigInteger id);
}
