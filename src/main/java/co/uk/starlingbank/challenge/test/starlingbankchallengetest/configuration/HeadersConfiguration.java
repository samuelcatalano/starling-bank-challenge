package co.uk.starlingbank.challenge.test.starlingbankchallengetest.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

/**
 * @author Samuel Catalano
 * @since 2019-11-22 23:19:12
 */

@Configuration
public class HeadersConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(HeadersConfiguration.class);

    @Value("${bearerToken}")
    private String bearerToken;

    /**
     * Create headers.
     * @return HttpHeaders
     */
    public HttpHeaders getHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + this.bearerToken);

        return headers;
    }
}