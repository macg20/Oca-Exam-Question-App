package pl.emgie.ocaexamquestionsapp.questions.domain.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.emgie.ocaexamquestionsapp.questions.dto.AttachmentDto;

@FeignClient("attachments-microservices-app")
public interface AtachmentServiceProxy {

    @RequestMapping("/attachments/test")
    String test();

    @PostMapping("/attachments/")
    String insertAttachment(AttachmentDto attachmentDto);

}
