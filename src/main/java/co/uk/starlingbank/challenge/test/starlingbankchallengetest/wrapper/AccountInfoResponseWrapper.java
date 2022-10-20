package co.uk.starlingbank.challenge.test.starlingbankchallengetest.wrapper;

import co.uk.starlingbank.challenge.test.starlingbankchallengetest.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Samuel Catalano
 * @since 2019-11-23 02:17:55
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountInfoResponseWrapper {

    @JsonProperty("accounts")
    private List<Account> accounts;
}