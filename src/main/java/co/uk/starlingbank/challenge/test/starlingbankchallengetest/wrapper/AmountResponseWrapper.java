package co.uk.starlingbank.challenge.test.starlingbankchallengetest.wrapper;

import co.uk.starlingbank.challenge.test.starlingbankchallengetest.entity.Amount;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Samuel Catalano
 * @since 2019-11-23 02:12:12
 */

@Data
public class AmountResponseWrapper implements Serializable {

    private Amount amount;
}
