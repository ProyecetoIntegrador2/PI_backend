package com.udea.autoevaluacion.services;

import org.springframework.stereotype.Service;

@Service
public class MetricsService {
    public int calculateAverageActualScore(int totalActual, int numOfQuestions) {
        return totalActual / numOfQuestions;
    }
    public int calculateAverageDesiredScore(int totalDesired, int numOfQuestions) {
        return totalDesired / numOfQuestions;
    }
}
