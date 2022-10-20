package co.uk.starlingbank.challenge.test.starlingbankchallengetest.util;

import co.uk.starlingbank.challenge.test.starlingbankchallengetest.entity.Transaction;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.exceptions.StarlingBankException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author Samuel Catalano
 * @since 2019-11-23 02:12:20
 */

@Component
public class BalanceRoundingUp {

    private static final Logger LOG = LoggerFactory.getLogger(BalanceRoundingUp.class);

    /**
     * Calculate the total rounded up.
     * @param transactions the list of transactions
     * @return BigDecimal the result
     *
     *@apiNote I know that there is a way to make this using stream() but I'm not sure how to do that yet (probably using reduce)
     */
    public BigDecimal calculate(final List<Transaction> transactions) throws StarlingBankException {
        BigDecimal result = BigDecimal.ZERO;
        for (final Transaction transaction : transactions) {
            if (transaction.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                result = result.add(this.roundingUpAmount(transaction));
            }
        }

        return result;
    }

    /**
     * Rounding up an amount.
     * @param transaction the transaction
     * @return BigDecimal rounded
     */
    private BigDecimal roundingUpAmount(final Transaction transaction) {
        final BigDecimal amount = transaction.getAmount();
        return amount.setScale(0, RoundingMode.UP).subtract(amount);
    }
}