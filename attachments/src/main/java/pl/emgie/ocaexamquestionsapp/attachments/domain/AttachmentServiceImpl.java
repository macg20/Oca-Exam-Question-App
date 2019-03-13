package pl.emgie.ocaexamquestionsapp.attachments.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.emgie.ocaexamquestionsapp.commons.exceptions.AttachmentRepositoryException;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

    private AttachmentRepository attachmentRepository;
    private FileStorageService fileStorageService;

    @Autowired
    public AttachmentServiceImpl(AttachmentRepository attachmentRepository, FileStorageService fileStorageService) {
        this.attachmentRepository = attachmentRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public BigInteger insert(String name, byte[] content) {
        Path path = fileStorageService.insertFile(name, content);
        AttachmentEntity attachment = createAttachment(name, path);
        attachment = attachmentRepository.save(attachment);
        return attachment.getId();
    }

    @Override
    public AttachmentDto read(BigInteger id) {
        AttachmentEntity attachmentEntity = attachmentRepository.findById(id).get();
        byte[] content = fileStorageService.readFile(attachmentEntity.getPath());
        return toDto(content);
    }

    @Override
    public void delete(BigInteger id) {
       Optional<AttachmentEntity> attachmentEntity = attachmentRepository.findById(id);
       attachmentEntity.ifPresent(this::deleteAttachment);
    }

    private void deleteAttachment(AttachmentEntity e) {
        fileStorageService.delete(e.getPath());
        attachmentRepository.delete(e);
    }

    private AttachmentEntity createAttachment(String name, Path path) {
        AttachmentEntity attachment = new AttachmentEntity();
        attachment.setName(name);
        attachment.setPath(path.toString());
        return attachment;
    }

    private AttachmentDto toDto(byte[] content) {
        AttachmentDto dto = new AttachmentDto();
        dto.setContent(content);
        return dto;
    }

}
