package com.studentplatform.backend;

import com.studentplatform.backend.model.Conference;
import com.studentplatform.backend.model.Profile;
import com.studentplatform.backend.model.User;
import com.studentplatform.backend.service.ConferenceService;
import com.studentplatform.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataSeeder {

    @Autowired
    private UserService userService;

    @Autowired
    private ConferenceService conferenceService;

    // @EventListener
    // public void onApplicationEvent(ContextRefreshedEvent event) {
    //     // Seed users if not exists
    //     if (userService.getAllUsers().isEmpty()) {
    //         seedUsers();
    //     }

    //     // Seed conferences if not exists
    //     if (conferenceService.getAllConferences().isEmpty()) {
    //         seedConferences();
    //     }
    // }

    private void seedUsers() {
        // Create admin user
        Profile adminProfile = new Profile(
            "System administrator for the conference management platform.",
            "Conference Organization",
            "System Administration",
            "5 years"
        );

        User admin = new User("Admin User", "admin@example.com", "password", User.Role.admin);
        admin.setProfile(adminProfile);
        userService.createUser(admin);

        // Create reviewer user
        Profile reviewerProfile = new Profile(
            "Professor of Computer Science with expertise in machine learning and AI.",
            "Research University",
            "Machine Learning",
            "10 years"
        );

        User reviewer = new User("Dr. Reviewer Smith", "reviewer@example.com", "password", User.Role.reviewer);
        reviewer.setProfile(reviewerProfile);
        userService.createUser(reviewer);

        // Create participant user
        Profile participantProfile = new Profile(
            "PhD student in Computer Science interested in AI and machine learning.",
            "Tech University",
            "Artificial Intelligence",
            "2 years"
        );

        User participant = new User("John Participant", "participant@example.com", "password", User.Role.participant);
        participant.setProfile(participantProfile);
        userService.createUser(participant);
    }

    private void seedConferences() {
        Conference conference1 = new Conference(
            "International Conference on Artificial Intelligence",
            "Join us for the premier AI conference featuring latest research and innovations in machine learning, deep learning, and AI applications.",
            LocalDate.of(2024, 3, 15),
            "Virtual",
            LocalDate.of(2024, 2, 28),
            500
        );
        conference1.setRegistered(342);
        conferenceService.createConference(conference1);

        Conference conference2 = new Conference(
            "Web Development Summit 2024",
            "A comprehensive summit on modern web development technologies, frameworks, and best practices.",
            LocalDate.of(2024, 2, 20),
            "New York, USA",
            LocalDate.of(2024, 1, 31),
            300
        );
        conference2.setStatus(Conference.Status.registration_closed);
        conference2.setRegistered(300);
        conferenceService.createConference(conference2);

        Conference conference3 = new Conference(
            "Healthcare Innovation Conference",
            "Exploring cutting-edge innovations in healthcare technology, medical research, and patient care systems.",
            LocalDate.of(2024, 4, 10),
            "Boston, USA",
            LocalDate.of(2024, 3, 15),
            250
        );
        conference3.setRegistered(128);
        conferenceService.createConference(conference3);
    }
}