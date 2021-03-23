package pl.pwpw.playground.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.pwpw.playground.application.Application;
import pl.pwpw.playground.application.ApplicationType;
import pl.pwpw.playground.service.ApplicationService;
import pl.pwpw.playground.application.ContactDetails;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping("/findAllApp")
    public List<Application> find()
    {
        return applicationService.findAllApplications();
    }

    @GetMapping("/contactDetailsNr")
    public ContactDetails findContactParam(@RequestParam String APP_NR) {

        return applicationService.findByNr(APP_NR);
    }

    @GetMapping("/findByEmail/{EMAIL_ADDRESS}")
    public String findByEmail(@PathVariable String EMAIL_ADDRESS) {
        Application as = applicationService.findByEmail(EMAIL_ADDRESS);
        ApplicationType at = as.getApplicationType();
        String nr =as.getApplicationNumber().getApplicationNumber();
        String lname = as.getLastName();
        String tmp = "ApplicationType: "+at+" ApplicationNumber: "+nr+" LastName: "+lname;
        return tmp;
    }
}