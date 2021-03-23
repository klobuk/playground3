package pl.pwpw.playground.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 *
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationNumber {

    private String applicationNumber;

}
