package com.harshjava.challengeapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ChallengeService {
    // private List<Challenge> challenges = new ArrayList<>();
    private Long nextId = 1l;

    @Autowired
    ChallengeRespository challengeRespository;

    public ChallengeService() {
        // Challenge challenge1 = new Challenge(1L, "January", "Learn a Programming
        // Language");
        // challenges.add(challenge1);
    }

    List<Challenge> getAllChallenges() {
        return challengeRespository.findAll();
    }

    boolean addChallenege(Challenge challenge) {
        if (challenge != null) {
            challenge.setId(nextId++);
            challengeRespository.save(challenge);
            return true;
        } else {
            return false;
        }
    }

    public Challenge getChallenge(String month) {
        Optional<Challenge> challenge = challengeRespository.findByMonthIgnoreCase(month);
        // for (Challenge challenge : challenges) {
        // if (challenge.getMonth().equalsIgnoreCase(month)) {
        // return challenge;
        // }
        // }
        return challenge.orElse(null);
    }

    public boolean updateChallenge(Long id, Challenge updatedChallenge) {
        Optional<Challenge> challenge = challengeRespository.findById(id);

        if (challenge.isPresent()) {
            Challenge challengeToUpdate = challenge.get();
            challengeToUpdate.setMonth(updatedChallenge.getMonth());
            challengeToUpdate.setDescription(updatedChallenge.getDescription());

            challengeRespository.save(challengeToUpdate);
            return true;
        }

        // for (Challenge challenge : challenges) {
        // if (challenge.getId().equals(id)) {
        // challenge.setMonth(updatedChallenge.getMonth());
        // challenge.setDescription(updatedChallenge.getDescription());
        // return true;
        // }
        // }
        return false;
    }

    public boolean deleteChallenge(Long id) {
        // return challenges.removeIf(challenge -> challenge.getId().equals(id));
        Optional<Challenge> challenge = challengeRespository.findById(id);

        if (challenge.isPresent()) {
            challengeRespository.deleteById(id);
            return true;
        }
        return false;
    }

}
