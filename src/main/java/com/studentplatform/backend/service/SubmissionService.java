package com.studentplatform.backend.service;

import com.studentplatform.backend.model.Submission;
import com.studentplatform.backend.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public Optional<Submission> getSubmissionById(Long id) {
        return submissionRepository.findById(id);
    }

    public List<Submission> getSubmissionsByUserId(Long userId) {
        return submissionRepository.findByUserId(userId);
    }

    public List<Submission> getSubmissionsByConferenceId(Long conferenceId) {
        return submissionRepository.findByConferenceId(conferenceId);
    }

    public Submission createSubmission(Submission submission) {
        return submissionRepository.save(submission);
    }

    public Submission updateSubmission(Submission submission) {
        return submissionRepository.save(submission);
    }

    public void deleteSubmission(Long id) {
        submissionRepository.deleteById(id);
    }
}