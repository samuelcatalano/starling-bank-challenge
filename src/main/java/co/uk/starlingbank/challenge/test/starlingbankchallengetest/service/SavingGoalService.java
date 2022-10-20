package co.uk.starlingbank.challenge.test.starlingbankchallengetest.service;

import co.uk.starlingbank.challenge.test.starlingbankchallengetest.configuration.HeadersConfiguration;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.entity.Amount;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.entity.SavingGoal;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.exceptions.StarlingBankException;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.util.StarlingBankAPIUrls;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.wrapper.AccountInfoResponseWrapper;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.wrapper.AmountResponseWrapper;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.wrapper.SavingGoalsResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Samuel Catalano
 * @since 2019-11-23 01:02:10
 */

@Service
public class SavingGoalService {

    private static final Logger LOG = LoggerFactory.getLogger(SavingGoalService.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HeadersConfiguration headersConfiguration;

    /**
     * Return list of saving goals
     * @return SavingGoalsResponseWrapper
     * @throws StarlingBankException
     */
    public SavingGoalsResponseWrapper getSavingsGoals() throws StarlingBankException {
        SavingGoalsResponseWrapper savingsGoalList;
        final Map<String, String> params = new HashMap<>();
        final String accountUid = this.getAccountUid();
        params.put("accountUid", accountUid);

        final HttpEntity<SavingGoalsResponseWrapper> request = this.getSavingGoalsInfoCustomHeaders();
        savingsGoalList = this.restTemplate.exchange(StarlingBankAPIUrls.API_STARLING_BANK_SAVINGS_GOALS_LIST_URL,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<SavingGoalsResponseWrapper>() {}, params).getBody();

        return savingsGoalList;
    }

    /**
     * Put savings
     * @param savingsGoalUUID uuid generated
     * @param roundedUpValue rounded value
     */
    public void putSavings(final String savingsGoalUUID, final BigDecimal roundedUpValue) throws StarlingBankException {
        final Map<String, String> params = new HashMap<>();
        final String uuid = UUID.randomUUID().toString();
        final String accountUid = this.getAccountUid();

        params.put("accountUid", accountUid);
        params.put("savingsGoalUid", savingsGoalUUID);
        params.put("transferUid", uuid);

        final HttpEntity<AmountResponseWrapper> request = this.getAmountCustomHeaders(roundedUpValue);
        this.restTemplate.exchange(StarlingBankAPIUrls.API_STARLING_BANK_PUT_SAVINGS_URL, HttpMethod.PUT, request, String.class, params);
    }

    /**
     * Get account id.
     * @return String with the account id
     */
    public String getAccountUid() throws StarlingBankException {
        final HttpEntity<AccountInfoResponseWrapper> request = this.getAccountInfoCustomHeaders();
        final AccountInfoResponseWrapper wrapper = this.restTemplate
                .exchange(StarlingBankAPIUrls.API_STARLING_BANK_ACCOUNT_INFO,
                        HttpMethod.GET,
                        request,
                        new ParameterizedTypeReference<AccountInfoResponseWrapper>() {})
                .getBody();

        return Objects.requireNonNull(wrapper).getAccounts().get(0).getAccountUid(); // this way because I only have one account
    }

    /**
     * Create savings.
     * @return uuid
     */
    public String createSavingsGoal() throws StarlingBankException {
        final Map<String, String> params = new HashMap<>();
        final String uuid = UUID.randomUUID().toString();
        params.put("savingsGoalUid", uuid);

        final HttpEntity<SavingGoal> request = this.getSavingGoalCustomHeaders();
        this.restTemplate.exchange(StarlingBankAPIUrls.API_STARLING_BANK_SAVINGS_GOALS_URL, HttpMethod.PUT, request, Void.class, params);

        return uuid;
    }

    /**
     * Get headers.
     * @return HttpEntity
     */
    private HttpEntity<SavingGoal> getSavingGoalCustomHeaders() {
        final HttpHeaders httpHeaders = this.headersConfiguration.getHeaders();
        final SavingGoal saving = SavingGoal.builder()
                .name("Challenge Savings Goal")
                .currency(Currency.getInstance("GBP"))
                .build();

        return new HttpEntity<>(saving, httpHeaders);
    }

    /**
     * Get headers.
     * @return HttpEntity
     */
    private HttpEntity<AccountInfoResponseWrapper> getAccountInfoCustomHeaders() {
        final HttpHeaders httpHeaders = this.headersConfiguration.getHeaders();
        return new HttpEntity<>(httpHeaders);
    }

    /**
     * Get headers.
     * @return HttpEntity
     */
    private HttpEntity<SavingGoalsResponseWrapper> getSavingGoalsInfoCustomHeaders() {
        final HttpHeaders httpHeaders = this.headersConfiguration.getHeaders();
        return new HttpEntity<>(httpHeaders);
    }

    /**
     * Get headers.
     * @param roundedUpValue the value rounded.
     * @return HttpEntity
     */
    private HttpEntity<AmountResponseWrapper> getAmountCustomHeaders(final BigDecimal roundedUpValue) {
        final HttpHeaders httpHeaders = this.headersConfiguration.getHeaders();

        final AmountResponseWrapper money = new AmountResponseWrapper();
        final Amount amount = new Amount();
        amount.setMinorUnits(roundedUpValue.movePointRight(2));
        amount.setCurrency(Currency.getInstance("GBP"));
        money.setAmount(amount);
        return new HttpEntity<>(money, httpHeaders);
    }
}