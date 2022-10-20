package co.uk.starlingbank.challenge.test.starlingbankchallengetest.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Currency;

/**
 * @author Samuel Catalano
 * @since 2019-11-23 01:18:06
 */

@Data
@Builder
public class SavingGoal implements Serializable {

    private String name;
    private Currency currency;
}