package co.uk.starlingbank.challenge.test.starlingbankchallengetest.wrapper;

import co.uk.starlingbank.challenge.test.starlingbankchallengetest.entity.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Samuel Catalano
 * @since 2019-11-23 02:03:14
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionsWrapper implements Serializable {

    private List<Transaction> transactions;
}
