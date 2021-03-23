package pl.pwpw.playground.attachment;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "FILE")
public class Attachment {

    @Id
    @SequenceGenerator(name = "app_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_id_seq")
    private long attachmentId;

    private byte [] attachmentBody;

    private LocalDate attachmentDate;

    private String attachmentType;

    private String AppNr;

    public Attachment(){}

    public Attachment(byte[] attachmentBody, LocalDate attachmentDate, String attachmentType, String AppNr) {
        this.attachmentBody = attachmentBody;
        this.attachmentDate = attachmentDate;
        this.attachmentType = attachmentType;
        this.AppNr = AppNr;
    }

}
