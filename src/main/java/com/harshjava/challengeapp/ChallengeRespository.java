package com.harshjava.challengeapp;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRespository extends JpaRepository<Challenge, Long> {

    Optional<Challenge> findByMonthIgnoreCase(String month);

}
