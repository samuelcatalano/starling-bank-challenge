package co.uk.starlingbank.challenge.test.starlingbankchallengetest.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SavingGoalsResponseWrapper {

    @JsonProperty("savingsGoalList")
    private List<SavingGoalResponseWrapper> savingGoals;
}