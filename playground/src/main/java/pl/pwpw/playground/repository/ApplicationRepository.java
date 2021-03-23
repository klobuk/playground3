package pl.pwpw.playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwpw.playground.application.Application;

import java.util.Optional;


public interface ApplicationRepository extends JpaRepository<Application, Long> {


    Optional<Application> findByContactDetails_EmailAddress_EmailAddress(String emailAddress);

    Optional<Application> findByApplicationNumber_ApplicationNumber(String applicationNumber);

    Application findTopByOrderByAppIdDesc();

}