package co.uk.starlingbank.challenge.test.starlingbankchallengetest.util;

/**
 * @author Samuel Catalano
 * @since 2019-11-23 12:31:31
 */
public final class StarlingBankAPIUrls {

    public static final String API_STARLING_BANK_TRANSACTIONS_URL = "https://api-sandbox.starlingbank.com/api/v1/transactions";
    public static final String API_STARLING_BANK_SAVINGS_GOALS_URL = "https://api-sandbox.starlingbank.com/api/v1/savings-goals/{savingsGoalUid}";
    public static final String API_STARLING_BANK_PUT_SAVINGS_URL = "https://api-sandbox.starlingbank.com/api/v2/account/{accountUid}/savings-goals/{savingsGoalUid}/add-money/{transferUid}";
    public static final String API_STARLING_BANK_ACCOUNT_INFO = "https://api-sandbox.starlingbank.com/api/v2/accounts";
    public static final String API_STARLING_BANK_SAVINGS_GOALS_LIST_URL = "https://api-sandbox.starlingbank.com/api/v2/account/{accountUid}/savings-goals";
}