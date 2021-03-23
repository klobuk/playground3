package pl.pwpw.playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwpw.playground.attachment.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
