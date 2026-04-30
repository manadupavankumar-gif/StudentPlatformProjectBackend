package com.studentplatform.backend.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Profile {

    private String bio;
    private String institution;
    private String specialization;
    private String experience;

    // Constructors
    public Profile() {}

    public Profile(String bio, String institution, String specialization, String experience) {
        this.bio = bio;
        this.institution = institution;
        this.specialization = specialization;
        this.experience = experience;
    }

    // Getters and Setters
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}