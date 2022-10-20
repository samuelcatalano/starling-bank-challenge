package co.uk.starlingbank.challenge.test.starlingbankchallengetest.service;

import co.uk.starlingbank.challenge.test.starlingbankchallengetest.configuration.HeadersConfiguration;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.entity.Transaction;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.exceptions.StarlingBankException;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.util.StarlingBankAPIUrls;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.wrapper.TransactionResponseWrapper;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.wrapper.TransactionsWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

/**
 * @author Samuel Catalano
 * @since 2019-11-23 00:31:41
 */

@Service
public class TransactionService {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HeadersConfiguration headersConfiguration;

    /**
     * Get transactions.
     * @return List of {@link Transaction}
     */
    public List<Transaction> getTransactions() throws StarlingBankException {
        final HttpEntity<TransactionsWrapper> request = this.getCustomHeaders();

        final TransactionResponseWrapper wrapper = this.restTemplate.exchange(StarlingBankAPIUrls.API_STARLING_BANK_TRANSACTIONS_URL,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<TransactionResponseWrapper>(){}).getBody();

        return Objects.requireNonNull(wrapper).getResponse().getTransactions();
    }

    /**
     * Get headers.
     * @return HttpEntity
     */
    private HttpEntity<TransactionsWrapper> getCustomHeaders() {
        final HttpHeaders httpHeaders = this.headersConfiguration.getHeaders();
        return new HttpEntity<>(httpHeaders);
    }
}