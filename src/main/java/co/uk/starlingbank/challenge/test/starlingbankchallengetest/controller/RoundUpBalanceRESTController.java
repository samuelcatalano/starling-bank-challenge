package co.uk.starlingbank.challenge.test.starlingbankchallengetest.controller;

import co.uk.starlingbank.challenge.test.starlingbankchallengetest.service.RoundUpBalanceService;
import co.uk.starlingbank.challenge.test.starlingbankchallengetest.wrapper.SavingGoalsResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Samuel Catalano
 * @since 2019-11-23 01:55:38
 */

@RestController
@RequestMapping(value = "/starling-bank-challenge")
public class RoundUpBalanceRESTController {

    @Autowired
    private RoundUpBalanceService service;

    @GetMapping(value = "/round-up")
    public ResponseEntity roundUp() {
        SavingGoalsResponseWrapper response = this.service.roundUp();
        return ResponseEntity.ok(response);
    }
}