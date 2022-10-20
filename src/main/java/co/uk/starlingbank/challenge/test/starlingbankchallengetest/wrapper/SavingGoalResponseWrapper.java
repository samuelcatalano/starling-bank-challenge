package co.uk.starlingbank.challenge.test.starlingbankchallengetest.wrapper;

import co.uk.starlingbank.challenge.test.starlingbankchallengetest.entity.Amount;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SavingGoalResponseWrapper {

    private String savingsGoalUid;
    private String name;
    @JsonProperty(value = "totalSaved")
    private Amount amount;
}