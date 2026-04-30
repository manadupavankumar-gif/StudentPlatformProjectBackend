package com.studentplatform.backend.controller;

import com.studentplatform.backend.model.Submission;
import com.studentplatform.backend.model.Conference;
import com.studentplatform.backend.model.User;
import com.studentplatform.backend.service.SubmissionService;
import com.studentplatform.backend.service.ConferenceService;
import com.studentplatform.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/submissions")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:5176"})
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllSubmissions() {
        List<Submission> submissions = submissionService.getAllSubmissions();
        return ResponseEntity.ok(Map.of(
            "success", true,
            "data", submissions
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubmissionById(@PathVariable Long id) {
        Optional<Submission> submission = submissionService.getSubmissionById(id);
        if (submission.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of(
            "success", true,
            "data", submission.get()
        ));
    }

    @PostMapping
    public ResponseEntity<?> createSubmission(@RequestBody Map<String, Object> submissionData) {
        try {
            Long conferenceId = Long.parseLong(submissionData.get("conferenceId").toString());
            Long userId = Long.parseLong(submissionData.get("userId").toString());

            Optional<Conference> conferenceOpt = conferenceService.getConferenceById(conferenceId);
            Optional<User> userOpt = userService.getUserById(userId);

            if (conferenceOpt.isEmpty() || userOpt.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Invalid conference or user ID"
                ));
            }

            Submission submission = new Submission();
            submission.setTitle((String) submissionData.get("title"));
            submission.setAbstractText((String) submissionData.get("abstract"));
            submission.setAuthors((String) submissionData.get("authors"));
            submission.setKeywords((List<String>) submissionData.get("keywords"));
            submission.setConference(conferenceOpt.get());
            submission.setUser(userOpt.get());
            submission.setFileUrl((String) submissionData.get("fileUrl"));

            Submission savedSubmission = submissionService.createSubmission(submission);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Submission created successfully",
                "data", savedSubmission
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", "Failed to create submission: " + e.getMessage()
            ));
        }
    }
}