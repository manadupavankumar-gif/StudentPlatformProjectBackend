package com.studentplatform.backend.controller;

import com.studentplatform.backend.model.Review;
import com.studentplatform.backend.model.Submission;
import com.studentplatform.backend.model.User;
import com.studentplatform.backend.service.ReviewService;
import com.studentplatform.backend.service.SubmissionService;
import com.studentplatform.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:5176"})
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(Map.of(
            "success", true,
            "data", reviews
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id) {
        Optional<Review> review = reviewService.getReviewById(id);
        if (review.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of(
            "success", true,
            "data", review.get()
        ));
    }

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody Map<String, Object> reviewData) {
        try {
            Long submissionId = Long.parseLong(reviewData.get("submissionId").toString());
            Long reviewerId = Long.parseLong(reviewData.get("reviewerId").toString());

            Optional<Submission> submissionOpt = submissionService.getSubmissionById(submissionId);
            Optional<User> reviewerOpt = userService.getUserById(reviewerId);

            if (submissionOpt.isEmpty() || reviewerOpt.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Invalid submission or reviewer ID"
                ));
            }

            Review review = new Review();
            review.setSubmission(submissionOpt.get());
            review.setReviewer(reviewerOpt.get());
            review.setOverallScore(Integer.parseInt(reviewData.get("overallScore").toString()));
            review.setCategoryScores((Map<String, Integer>) reviewData.get("categoryScores"));
            review.setStrengths((List<String>) reviewData.get("strengths"));
            review.setWeaknesses((List<String>) reviewData.get("weaknesses"));
            review.setComments((String) reviewData.get("comments"));
            review.setRecommendation((String) reviewData.get("recommendation"));

            Review savedReview = reviewService.createReview(review);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Review created successfully",
                "data", savedReview
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", "Failed to create review: " + e.getMessage()
            ));
        }
    }
}