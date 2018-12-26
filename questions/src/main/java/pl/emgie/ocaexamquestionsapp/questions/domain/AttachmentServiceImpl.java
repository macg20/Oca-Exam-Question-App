package pl.emgie.ocaexamquestionsapp.questions.domain;

import org.springframework.stereotype.Service;
import pl.emgie.ocaexamquestionsapp.exceptions.AttachmentRepositoryException;
import pl.emgie.ocaexamquestionsapp.questions.dto.AttachmentDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
class AttachmentServiceImpl implements AttachmentService {

    private Path repositoryPath;

    public AttachmentServiceImpl() {
        this.repositoryPath = generateAttachmentRepositoryPath();
    }

    @Override
    public Path insert(String name, byte[] content) {
        try {
            String fileName = repositoryPath.toString() +"\\" +name;
            Path path = Paths.get(fileName);
            Files.write(path, content);
            return path;
        } catch (IOException e) {
            getLogger().error("Cannot insert attachment",e);
            throw new AttachmentRepositoryException("Cannot insert attachment",e);
        }
    }

    @Override
    public AttachmentDto read(String path) {
        try {
            byte[] content = Files.readAllBytes(Paths.get(path));
            return toDto(content);
        } catch (IOException e) {
            getLogger().error("Cannot read attachment",e);
            throw new AttachmentRepositoryException("Cannot read attachment",e);
        }
    }

    @Override
    public void delete(String path) {
        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            getLogger().error("Cannot delete attachment",e);
            throw new AttachmentRepositoryException("Cannot delete attachment",e);
        }
    }

    private Path generateAttachmentRepositoryPath() {
        String userdir = System.getProperty("user.home");
        String repositoryPath = userdir + ("\\attachments");
        if (!Files.exists(Paths.get(repositoryPath))) {
            try {
                Set<PosixFilePermission> permissions = fullyPermissions();
                FileAttribute<Set<PosixFilePermission>> attributes = PosixFilePermissions.asFileAttribute(permissions);
                Files.createDirectory(Paths.get(repositoryPath),attributes);
            } catch (IOException e) {
                getLogger().error("Cannot initialize attachment repository" ,e);
                throw new AttachmentRepositoryException("Cannot initialize attachment repository" ,e);
            }
        }
        return Paths.get(repositoryPath);
    }

    private Set<PosixFilePermission> fullyPermissions() {
        Set<PosixFilePermission> filePermissions = new HashSet<>();
        filePermissions.add(PosixFilePermission.OTHERS_EXECUTE);
        filePermissions.add(PosixFilePermission.OTHERS_WRITE);
        filePermissions.add(PosixFilePermission.OTHERS_READ);
        filePermissions.add(PosixFilePermission.GROUP_EXECUTE);
        filePermissions.add(PosixFilePermission.GROUP_WRITE);
        filePermissions.add(PosixFilePermission.GROUP_READ);
        filePermissions.add(PosixFilePermission.OWNER_EXECUTE);
        filePermissions.add(PosixFilePermission.OWNER_READ);
        filePermissions.add(PosixFilePermission.OWNER_WRITE);
        return filePermissions;
    }

    private AttachmentDto toDto(byte[] content) {
        AttachmentDto dto = new AttachmentDto();
        dto.setContent(content);
        return dto;
    }


}
