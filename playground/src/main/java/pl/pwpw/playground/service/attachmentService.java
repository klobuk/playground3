package pl.pwpw.playground.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.pwpw.playground.application.Application;
import pl.pwpw.playground.repository.AttachmentRepository;
import pl.pwpw.playground.attachment.Attachment;
import pl.pwpw.playground.repository.ApplicationRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class attachmentService {

    private final AttachmentRepository attachmentRepository;

    private final ApplicationRepository applicationRepository;

    public Attachment store(MultipartFile file, String appNr) {
        LocalDate nowDate = LocalDate.now();
        Attachment attachment = null;
        try {
            attachment = new Attachment(file.getBytes(), nowDate, file.getContentType(), appNr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attachmentRepository.save(attachment);
    }
//-------------------------------------------------------------------------------------------------------------------
    public boolean appNrExist(String appNr)
    {
        ApplicationService applicationService = new ApplicationService(applicationRepository);
        List<Application> applicationList = applicationService.findAllApplications();
        boolean exist = false;
        for(int i = 0; i<applicationList.size(); i++)
        {
            if(applicationList.get(i).getApplicationNumber().getApplicationNumber().equals(appNr))
                exist = true;
        }
        return exist;
    }

    public List<Attachment> getFiles()
    {
        return attachmentRepository.findAll();
    }
}
