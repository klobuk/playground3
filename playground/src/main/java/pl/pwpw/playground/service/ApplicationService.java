package pl.pwpw.playground.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pwpw.playground.application.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import pl.pwpw.playground.repository.ApplicationRepository;
@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public List<Application> findAllApplications()
    {
        return applicationRepository.findAll();
    }

    public ContactDetails findByNr(String nr) {
        Optional<Application> fromDatabase = applicationRepository.findByApplicationNumber_ApplicationNumber(nr);
        if(fromDatabase.isEmpty()) {
            throw new NullPointerException("Brak aplikacji o podanym numerze !!!");
        }
        ContactDetails newContactD = fromDatabase.get().getContactDetails();
        return newContactD;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Application findByEmail(String email) {
        Optional<Application> fromDatabase = applicationRepository.findByContactDetails_EmailAddress_EmailAddress(email);
        if(fromDatabase.isEmpty()) {
            throw new NullPointerException("Brak aplikacji o podanym emailu !!!");
        }
        return fromDatabase.get();
    }
    public boolean rightEmail(String email)
    {
        return email.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b");
    }
    public boolean rightPhoneNumber(String phone)
    {
        return phone.matches("^[0-9]{9}$");
    }

    //------------------------------------------------------------------------------------------------------------------

    public Application storeApp(String firstName, String lastName, String emailAddress, String phoneNumber, String applicationType) {
        Application appToSave = new Application();;
        appToSave.setApplicationNumber(createAppNr());
        appToSave.setFirstName(firstName);
        appToSave.setLastName(lastName);
        appToSave.setContactDetails(newContactDetails(emailAddress, phoneNumber));
        ApplicationType appType = ApplicationType.valueOf(applicationType);
        if(firstName == null||lastName==null||emailAddress==null||phoneNumber==null||firstName == ""||lastName==""||emailAddress==""||phoneNumber=="")
        {
            throw new IllegalArgumentException("Jeden lub więcej parametrow jest pustych !!!");
        }
        appToSave.setApplicationType(appType);
        return applicationRepository.save(appToSave);
    }

    //------------------------------------------------------------------------------------------------------------------

    public ContactDetails newContactDetails(String emailAddress, String phoneNumber){
        boolean rightValue = rightEmail(emailAddress);
        EmailAddress email = new EmailAddress();
        if(rightValue == true)
        {
            email.setEmailAddress(emailAddress);
        }
        else
        {
            throw new IllegalArgumentException("Adres email jest nieprawidlowy !!!");
        }
        PhoneNumber phone = new PhoneNumber();
        rightValue = rightPhoneNumber(phoneNumber);
        if (rightValue==true)
        {
            phone.setPhoneNumber(phoneNumber);
        }
        else
        {
            throw new IllegalArgumentException("Podany numer telefonu jest nieprawidlowy !!!");
        }
        ContactDetails contactDetails;
        return contactDetails = new ContactDetails(email, phone);
    }

    //------------------------------------------------------------------------------------------------------------------

    public ApplicationNumber createAppNr() {
        StringBuilder sb = new StringBuilder();

        Application topApplicationById = applicationRepository.findTopByOrderByAppIdDesc();
        String numberOfTopApp = topApplicationById.getApplicationNumber().getApplicationNumber();
        String stringDateFromTopApp = numberOfTopApp.substring(3,13);
        int howManyOfTopDate = Integer.parseInt(numberOfTopApp.substring(14));
        LocalDate nowDate = LocalDate.now();
        String stringDate = nowDate.toString();
        int newNumber = 0;
        if(stringDateFromTopApp.equals(stringDate)){
            newNumber = howManyOfTopDate+1;
        }
        else {
            newNumber = 1;
        }
        sb.append("PL/").append(stringDate).append("/").append(newNumber);
        ApplicationNumber applicationNumber = new ApplicationNumber(sb.toString());
        return applicationNumber;
    }

}