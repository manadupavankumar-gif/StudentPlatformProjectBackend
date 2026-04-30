package com.studentplatform.backend.service;

import com.studentplatform.backend.model.Conference;
import com.studentplatform.backend.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    public List<Conference> getAllConferences() {
        return conferenceRepository.findAll();
    }

    public Optional<Conference> getConferenceById(Long id) {
        return conferenceRepository.findById(id);
    }

    public Conference createConference(Conference conference) {
        return conferenceRepository.save(conference);
    }

    public Conference updateConference(Conference conference) {
        return conferenceRepository.save(conference);
    }

    public void deleteConference(Long id) {
        conferenceRepository.deleteById(id);
    }
}