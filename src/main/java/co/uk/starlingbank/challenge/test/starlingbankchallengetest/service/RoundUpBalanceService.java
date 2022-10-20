package co.uk.starlingbank.challenge.test.starlingbankchallengetest.service;

import co.uk.starlingbank.challenge.test.starlingbankchallengetest.entity.Transaction;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.exceptions.ServiceException;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.exceptions.StarlingBankException;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.util.BalanceRoundingUp;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.wrapper.SavingGoalsResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Samuel Catalano
 * @since 2019-11-23 00:27:45
 */

@Service
public class RoundUpBalanceService {

    private static final Logger LOG = LoggerFactory.getLogger(RoundUpBalanceService.class);

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private SavingGoalService savingGoalService;
    @Autowired
    private BalanceRoundingUp balanceRoundingUp;

    /**
     * Create the round up process.
     * @return String with the UUID generated
     */
    public SavingGoalsResponseWrapper roundUp() {
        SavingGoalsResponseWrapper response;
        try {
            final List<Transaction> transactions = this.transactionService.getTransactions();
            final BigDecimal roundedupAmount = this.balanceRoundingUp.calculate(transactions);
            final String UUID = this.savingGoalService.createSavingsGoal();
            this.savingGoalService.putSavings(UUID, roundedupAmount);
            response = this.savingGoalService.getSavingsGoals();
        } catch (StarlingBankException e) {
            LOG.error("Error processing round up transactions", e);
            throw new ServiceException(e.getCause());
        }
        return response;
    }
}