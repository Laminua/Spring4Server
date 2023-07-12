package com.example.springexercise3boot.util;

import com.example.springexercise3boot.dto.UserProfileDTO;
import com.example.springexercise3boot.models.user.UserProfile;
import com.example.springexercise3boot.services.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserProfileValidator implements Validator {

    private final UserProfileService userProfileService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserProfileDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserProfileDTO userProfileDTO = (UserProfileDTO) target;

        if (userProfileDTO.getId() == 0) {
            validateNewUserProfile(userProfileDTO, errors);
        } else {
            validateExistingUserProfile(userProfileDTO, errors);
        }
    }

    private void validateNewUserProfile(UserProfileDTO userProfileDTO, Errors errors) {
        if (userProfileService.findByUsername(userProfileDTO.getUsername()) != null) {
            errors.rejectValue("username", "400", "Username already exists");
        }

        if (userProfileService.findByEmail(userProfileDTO.getEmail()) != null) {
            errors.rejectValue("email", "400", "Email already taken");
        }
    }

    private void validateExistingUserProfile(UserProfileDTO userProfileDTO, Errors errors) {
        UserProfile userProfile = userProfileService.findOne(userProfileDTO.getId());

        if (!userProfile.getUsername().equals(userProfileDTO.getUsername()) && userProfileService.findByUsername(userProfileDTO.getUsername()) != null) {
            errors.rejectValue("username", "400", "Username already exists");
        }

        if (!userProfile.getEmail().equals(userProfileDTO.getEmail()) && userProfileService.findByEmail(userProfileDTO.getEmail()) != null) {
            errors.rejectValue("email", "400", "Email already taken");
        }
    }
}
