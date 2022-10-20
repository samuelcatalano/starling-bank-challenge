package co.uk.starlingbank.challenge.test.starlingbankchallengetest.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Samuel Catalano
 * @since 2019-11-23 02:19:25
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResponseWrapper implements Serializable {

    @JsonProperty("_embedded")
    private TransactionsWrapper response;

}