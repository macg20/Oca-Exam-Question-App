package pl.emgie.ocaexamquestionsapp.attachments.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

@Service
class FileStorageService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    private FilePermissionService filePermissionService;
    private Path repositoryPath;

    @Autowired
    public FileStorageService(FilePermissionService filePermissionService) {
        this.filePermissionService = filePermissionService;
        this.repositoryPath = generateAttachmentStorageRepositoryPath();
    }

    Path insertFile(String name, byte[] content) {
        try {
            String fileName = repositoryPath.toString() + "\\" + name;
            Path path = Paths.get(fileName);
            Files.write(path, content);
            return path;
        } catch (Exception e) {
            logger.error("Cannot insert attachment", e);
            throw new AttachmentRepositoryException("Cannot insert attachment", e);
        }
    }

    byte[] readFile(String path) {
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            logger.error("Cannot read attachment", e);
            throw new AttachmentRepositoryException("Cannot read attachment", e);
        }
    }


    void delete(String path) {
        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            logger.error("Cannot delete attachment", e);
            throw new AttachmentRepositoryException("Cannot delete attachment", e);
        }
    }


    private Path generateAttachmentStorageRepositoryPath() {
        String userdir = System.getProperty("user.home");
        String storageRepositoryPath = userdir + ("\\attachments");
        if (!Files.exists(Paths.get(storageRepositoryPath))) {
            try {
                Set<PosixFilePermission> permissions = filePermissionService.createDirectoryFullPermission();
                FileAttribute<Set<PosixFilePermission>> attributes = PosixFilePermissions.asFileAttribute(permissions);
                Files.createDirectory(Paths.get(storageRepositoryPath), attributes);
            } catch (IOException e) {
                logger.error("Cannot initialize file storage repository", e);
                throw new AttachmentRepositoryException("Cannot initialize file storage repository", e);
            }
        }
        return Paths.get(storageRepositoryPath);
    }
}
