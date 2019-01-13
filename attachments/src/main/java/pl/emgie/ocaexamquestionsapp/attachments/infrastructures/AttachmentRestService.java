package pl.emgie.ocaexamquestionsapp.attachments.infrastructures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.emgie.ocaexamquestionsapp.attachments.domain.AttachmentDto;
import pl.emgie.ocaexamquestionsapp.attachments.domain.AttachmentService;

import java.nio.file.Path;

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
        Path filePath = attachmentService.insert(attachmentDto.getName(), attachmentDto.getContent());
        return ResponseEntity.ok(filePath.toAbsolutePath());
    }

    @GetMapping(value = "/", params = {"path"})
    AttachmentDto readAttachment(@RequestParam("path") String path) {
        AttachmentDto dto = attachmentService.read(path);
        return dto;
    }

}
