package com.studentplatform.backend.model;

import jakarta.persistence.Embeddable;
import java.util.List;

@Embeddable
public class AIFeedback {

    private Double score;
    private List<String> strengths;
    private List<String> improvements;
    private String recommendation;

    // Constructors
    public AIFeedback() {}

    public AIFeedback(Double score, List<String> strengths, List<String> improvements, String recommendation) {
        this.score = score;
        this.strengths = strengths;
        this.improvements = improvements;
        this.recommendation = recommendation;
    }

    // Getters and Setters
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getImprovements() {
        return improvements;
    }

    public void setImprovements(List<String> improvements) {
        this.improvements = improvements;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}