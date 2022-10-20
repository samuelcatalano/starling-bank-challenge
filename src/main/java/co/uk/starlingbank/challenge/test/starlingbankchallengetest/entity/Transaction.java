package co.uk.starlingbank.challenge.test.starlingbankchallengetest.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 * @author Samuel Catalano
 * @since 2019-11-22 23:59:04
 */

@Data
public class Transaction implements Serializable {

    private String id;
    private Date created;
    private Currency currency;
    private BigDecimal balance;
    private BigDecimal amount;
    private String narrative;
    private String direction;
    private String source;

}
