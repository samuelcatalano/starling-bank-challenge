package co.uk.starlingbank.challenge.test.starlingbankchallengetest.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Samuel Catalano
 * @since 2019-11-23 01:24:11
 */

@Data
public class Amount implements Serializable {

    private Currency currency;
    private BigDecimal minorUnits;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getMinorUnits() {
        return minorUnits;
    }

    public void setMinorUnits(BigDecimal minorUnits) {
        this.minorUnits = minorUnits;
    }
}