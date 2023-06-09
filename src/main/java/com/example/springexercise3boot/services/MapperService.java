package com.example.springexercise3boot.services;

import com.example.springexercise3boot.dto.TestDescriptionDTO;
import com.example.springexercise3boot.dto.TestWithQuestionsDTO;
import com.example.springexercise3boot.dto.UserProfileDTO;
import com.example.springexercise3boot.models.test.Test;
import com.example.springexercise3boot.models.user.UserProfile;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

    public UserProfile convertToUserProfile(UserProfileDTO profileDTO) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(profileDTO.getId());
        userProfile.setUsername(profileDTO.getUsername());
        userProfile.setPassword(profileDTO.getPassword());
        userProfile.setRole(profileDTO.getRole());
        userProfile.setName(profileDTO.getName());
        userProfile.setEmail(profileDTO.getEmail());

        return userProfile;
    }

    public UserProfileDTO convertToUserProfileDTO(UserProfile userProfile) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setId(userProfile.getId());
        userProfileDTO.setUsername(userProfile.getUsername());
        userProfileDTO.setPassword(userProfile.getPassword());
        userProfileDTO.setName(userProfile.getName());
        userProfileDTO.setRole(userProfile.getRole());
        userProfileDTO.setEmail(userProfile.getEmail());

        return userProfileDTO;
    }

    public TestDescriptionDTO convertToTestDescriptionDTO(Test test) {
        TestDescriptionDTO testDescriptionDTO = new TestDescriptionDTO();
        testDescriptionDTO.setId(test.getId());
        testDescriptionDTO.setDescription(test.getDescription());

        return testDescriptionDTO;
    }

    public TestWithQuestionsDTO convertToTestWithQuestionsDTO(Test test) {
        TestWithQuestionsDTO dto = new TestWithQuestionsDTO();
        dto.setId(test.getId());
        dto.setDescription(test.getDescription());
        dto.setQuestions(test.getQuestions());
        dto.setMax_attempts(test.getMax_attempts());

        return dto;
    }
}
