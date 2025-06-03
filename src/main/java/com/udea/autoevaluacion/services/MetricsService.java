package com.udea.autoevaluacion.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.udea.autoevaluacion.models.SubmissionAnswer;

@Service
public class MetricsService {
    private final int Threshold = 60;
    private final int MajorityCutOff = 60;
    private final int MaxLevel = 5;
    private final int MinLevel = 1;

    public int calculateAverage(int total, int numOfQuestions) {
        return total / numOfQuestions;
    }

    public int calculateThresholdBasedScoring(List<SubmissionAnswer> submissionAnswers) {
        Map<Integer, Double> levelThresholds = new HashMap<>();
        levelThresholds.put(1, 100.0);
        levelThresholds.put(2, 80.0);
        levelThresholds.put(3, 60.0);
        levelThresholds.put(4, 50.0);
        levelThresholds.put(5, 40.0);

        for (int currentLevel = MaxLevel; currentLevel >= MinLevel; currentLevel--) {
            final int level = currentLevel;
            Double currentThreshold = levelThresholds.get(currentLevel);

            long totalAnswersLevel = submissionAnswers.stream()
                    .mapToInt(answer -> answer.getActualOption().getOptionLevel())
                    .filter(l -> l >= level)
                    .count();

            Double actualPercentage = (totalAnswersLevel / (double) submissionAnswers.size()) * 100;

            if (actualPercentage >= currentThreshold) {
                return currentLevel;
            }
        }
        return 0;
    }

    public int calculateMajorityCutOffLevel(List<SubmissionAnswer> submissionAnswers) {
        List<Integer> levels = submissionAnswers.stream()
                .map(answer -> answer.getActualOption().getOptionLevel())
                .collect(Collectors.toList());

        Collections.sort(levels);

        long cumulativeCount = 0;
        for (int i = 0; i < submissionAnswers.size(); i++) {
            cumulativeCount++;

            double percentage = (cumulativeCount / (double) submissionAnswers.size()) * 100;

            if (percentage >= 50.0) {
                return levels.get(i);
            }
        }
        return 0;
    }

    public int calculateQualifiedMajorityCriterion(List<SubmissionAnswer> submissionAnswers) {
        for (int currentLevel = MaxLevel; currentLevel >= MinLevel; currentLevel--) {
            final int current = currentLevel;

            long count = submissionAnswers.stream()
                    .mapToInt(answer -> answer.getActualOption().getOptionLevel())
                    .filter(level -> level >= current)
                    .count();

            double percentage = (count / (double) submissionAnswers.size()) * 100;

            if (percentage >= MajorityCutOff) {
                return currentLevel;
            }
        }
        return 0;
    }
}