package com.studentplatform.backend.repository;

import com.studentplatform.backend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findBySubmissionId(Long submissionId);

    List<Review> findByReviewerId(Long reviewerId);
}