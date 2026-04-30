package com.studentplatform.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Submission submission;

    @Column(name = "submission_title")
    private String submissionTitle;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User reviewer;

    @Column(name = "reviewer_name")
    private String reviewerName;

    @NotNull
    private Integer overallScore;

    @ElementCollection
    @CollectionTable(name = "review_category_scores",
        joinColumns = @JoinColumn(name = "review_id"))
    @MapKeyColumn(name = "category")
    @Column(name = "score")
    private Map<String, Integer> categoryScores;

    @ElementCollection
    @CollectionTable(name = "review_strengths",
        joinColumns = @JoinColumn(name = "review_id"))
    @Column(name = "strength")
    private java.util.List<String> strengths;

    @ElementCollection
    @CollectionTable(name = "review_weaknesses",
        joinColumns = @JoinColumn(name = "review_id"))
    @Column(name = "weakness")
    private java.util.List<String> weaknesses;

    @Column(length = 2000)
    private String comments;

    private String recommendation;

    @Column(name = "submitted_date")
    private LocalDateTime submittedDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (submittedDate == null) {
            submittedDate = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Constructors
    public Review() {}

    public Review(Submission submission, User reviewer, Integer overallScore,
                 Map<String, Integer> categoryScores, java.util.List<String> strengths,
                 java.util.List<String> weaknesses, String comments, String recommendation) {
        this.submission = submission;
        this.submissionTitle = submission.getTitle();
        this.reviewer = reviewer;
        this.reviewerName = reviewer.getName();
        this.overallScore = overallScore;
        this.categoryScores = categoryScores;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.comments = comments;
        this.recommendation = recommendation;
        this.status = Status.completed;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
        if (submission != null) {
            this.submissionTitle = submission.getTitle();
        }
    }

    public String getSubmissionTitle() {
        return submissionTitle;
    }

    public void setSubmissionTitle(String submissionTitle) {
        this.submissionTitle = submissionTitle;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
        if (reviewer != null) {
            this.reviewerName = reviewer.getName();
        }
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public Integer getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Integer overallScore) {
        this.overallScore = overallScore;
    }

    public Map<String, Integer> getCategoryScores() {
        return categoryScores;
    }

    public void setCategoryScores(Map<String, Integer> categoryScores) {
        this.categoryScores = categoryScores;
    }

    public java.util.List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(java.util.List<String> strengths) {
        this.strengths = strengths;
    }

    public java.util.List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(java.util.List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public LocalDateTime getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(LocalDateTime submittedDate) {
        this.submittedDate = submittedDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public enum Status {
        pending, completed
    }
}