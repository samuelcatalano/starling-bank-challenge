package co.uk.starlingbank.challenge.test.starlingbankchallengetest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Samuel Catalano
 * @since 2019-11-22 23:55:38
 */

@Configuration
public class RESTConfiguration {

    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }
}