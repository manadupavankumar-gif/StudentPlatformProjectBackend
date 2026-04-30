package com.studentplatform.backend.controller;

import com.studentplatform.backend.model.Conference;
import com.studentplatform.backend.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/conferences")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:5176"})
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    @GetMapping
    public ResponseEntity<?> getAllConferences() {
        List<Conference> conferences = conferenceService.getAllConferences();
        return ResponseEntity.ok(Map.of(
            "success", true,
            "data", conferences
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getConferenceById(@PathVariable Long id) {
        Optional<Conference> conference = conferenceService.getConferenceById(id);
        if (conference.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of(
            "success", true,
            "data", conference.get()
        ));
    }

    @PostMapping
    public ResponseEntity<?> createConference(@RequestBody Map<String, Object> conferenceData) {
        try {
            Conference conference = new Conference();
            conference.setTitle((String) conferenceData.get("title"));
            conference.setDescription((String) conferenceData.get("description"));
            conference.setDate(LocalDate.parse((String) conferenceData.get("date")));
            conference.setLocation((String) conferenceData.get("location"));
            conference.setDeadline(LocalDate.parse((String) conferenceData.get("deadline")));
            conference.setCapacity(Integer.parseInt(conferenceData.get("capacity").toString()));

            Conference savedConference = conferenceService.createConference(conference);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Conference created successfully",
                "data", savedConference
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", "Failed to create conference: " + e.getMessage()
            ));
        }
    }
}