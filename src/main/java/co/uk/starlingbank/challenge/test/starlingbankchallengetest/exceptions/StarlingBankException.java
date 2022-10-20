package co.uk.starlingbank.challenge.test.starlingbankchallengetest.exceptions;

/**
 * @author Samuel Catalano
 * @since 2019-11-23 18:41:12
 */
public class StarlingBankException extends Exception {

    public StarlingBankException(String message) {
        super(message);
    }

    public StarlingBankException(String message, Throwable cause) {
        super(message, cause);
    }

    public StarlingBankException(Throwable cause) {
        super(cause);
    }
}