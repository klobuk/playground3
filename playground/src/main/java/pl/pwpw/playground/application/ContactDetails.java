package pl.pwpw.playground.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 *
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ContactDetails {

    @Embedded
    private EmailAddress emailAddress;

    @Embedded
    private PhoneNumber phoneNumber;


}
