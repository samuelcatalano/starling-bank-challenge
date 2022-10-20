package co.uk.starlingbank.challenge.test.starlingbankchallengetest;

import co.uk.starlingbank.challenge.test.starlingbankchallengetest.entity.Transaction;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.exceptions.ServiceException;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.exceptions.StarlingBankException;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.service.RoundUpBalanceService;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.service.SavingGoalService;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.service.TransactionService;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.wrapper.SavingGoalsResponseWrapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class StarlingBankChallengeTestApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SavingGoalService savingGoalService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private RoundUpBalanceService roundUpBalanceService;

    /**
     * Initial setup.
     */
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Asserting the context loads.
     * @throws Exception the exception to be launched
     */
    @Test
    public void testContexLoads() throws Exception {
        assertThat(this.savingGoalService).isNotNull();
    }

    /**
     * Asserting the get transactions API.
     */
    @Test
    public void testGetTransactions() {
        List<Transaction> transactions;
        try {
            transactions = this.transactionService.getTransactions();
            assertNotNull(transactions);
        } catch (StarlingBankException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Asserting the save goals API.
     */
    @Test
    public void testSavingNewGoal() {
        String UUID;
        try {
            UUID = this.savingGoalService.createSavingsGoal();
            assertNotNull(UUID);
        } catch (StarlingBankException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Asserting the round up API.
     */
    @Test
    public void testRoundUpAmount() {
        SavingGoalsResponseWrapper savingGoals = this.roundUpBalanceService.roundUp();
        assertNotNull(savingGoals);
    }

    /**
     * Asserting the main controller API.
     * @throws Exception the exception to be launched
     */
    @Test
    public void testControllerAPI() throws Exception{
        this.mockMvc.perform(get("/starling-bank-challenge/round-up"))
                .andDo(print())
                .andExpect(status()
                .isOk());
    }
}