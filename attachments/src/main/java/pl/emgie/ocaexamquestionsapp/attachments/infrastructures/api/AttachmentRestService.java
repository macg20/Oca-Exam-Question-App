package pl.emgie.ocaexamquestionsapp.attachments.infrastructures.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.emgie.ocaexamquestionsapp.attachments.domain.AttachmentDto;
import pl.emgie.ocaexamquestionsapp.attachments.domain.AttachmentService;

import java.math.BigInteger;

@RestController
@RequestMapping(value = "/attachments")
class AttachmentRestService {

    private AttachmentService attachmentService;

    @Autowired
    AttachmentRestService(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/")
    ResponseEntity<?> insertAttachment(@RequestBody AttachmentDto attachmentDto) {
        BigInteger id = attachmentService.insert(attachmentDto.getName(), attachmentDto.getContent());
        return ResponseEntity.ok(id);
    }

    @GetMapping(value = "/{id}")
    AttachmentDto readAttachment(@PathVariable("id") BigInteger id) {
        AttachmentDto dto = attachmentService.read(id);
        return dto;
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteAttachment(@PathVariable("id") BigInteger id) {
        attachmentService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
