package com.challengeapp.ChallengeApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {


    private ChallengeServices challengeServices;

    public ChallengeController(ChallengeServices challengeServices){
        this.challengeServices=challengeServices;
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges(){

        return new ResponseEntity<>(challengeServices.getAllChallenges(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addChallenge(@RequestBody Challenge challenge){
        boolean isChallengeAdded=challengeServices.addChallenge(challenge);
        if(isChallengeAdded)
        return new ResponseEntity<>("Challenge added successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Challenge Not added successfully",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{month}")
    public ResponseEntity<Challenge> getChallenge(@PathVariable String month){
        Challenge challenge=challengeServices.getChallenge(month);
        if(challenge!=null)
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable Long id, @RequestBody Challenge updatedChallenge){
        boolean isUpdatedChallenge=challengeServices.updateChallenge(id,updatedChallenge);
        if(isUpdatedChallenge){
            return new ResponseEntity<>("Challenge updated successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Challenge not updated successfully",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(Long id){
        boolean isChallengeDeleted=challengeServices.deleteChallenge(id);
        if(isChallengeDeleted){
            return new ResponseEntity<>("Challenge deleted successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Challenge not deleted successfully",HttpStatus.NOT_FOUND);
        }
    }
}
