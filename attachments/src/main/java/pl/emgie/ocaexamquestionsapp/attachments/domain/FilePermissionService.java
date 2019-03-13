package pl.emgie.ocaexamquestionsapp.attachments.domain;

import org.springframework.stereotype.Service;

import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

@Service
class FilePermissionService {

    Set<PosixFilePermission> createDirectoryFullPermission() {
       return Set.of(PosixFilePermission.OTHERS_EXECUTE,PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_READ,
                PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.GROUP_WRITE,PosixFilePermission.GROUP_READ,
                PosixFilePermission.OWNER_EXECUTE, PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE);
    }
}
