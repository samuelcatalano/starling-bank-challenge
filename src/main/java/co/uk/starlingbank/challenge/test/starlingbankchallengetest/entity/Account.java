package co.uk.starlingbank.challenge.test.starlingbankchallengetest.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Samuel Catalano
 * @since 2019-11-23 00:55:28
 */

@Data
public class Account {

    private String accountUid;
    private String defaultCategory;
    private String currency;
    private Date createdAt;

}