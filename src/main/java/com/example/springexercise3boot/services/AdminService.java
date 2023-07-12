package com.example.springexercise3boot.services;

import com.example.springexercise3boot.dto.UserProfileDTO;
import com.example.springexercise3boot.models.user.UserProfile;
import com.example.springexercise3boot.util.UserProfileValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final UserProfileService userProfileService;

    private final MapperService mapper;

    private final UserProfileValidator userProfileValidator;

    public List<UserProfileDTO> getUsers() {

        return userProfileService.findAll().stream()
                .map(mapper::convertToUserProfileDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> addUser(UserProfileDTO profileDTO, BindingResult bindingResult)
            throws BindException {

        userProfileValidator.validate(profileDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            log.error("API: User can't be added because of invalid data");
            throw new BindException(bindingResult);
        }

        userProfileService.save(mapper.convertToUserProfile(profileDTO));
        log.debug("API: User successfully created");

        return new ResponseEntity<>("API: User successfully created", HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteUserById(long id) {

        if (userProfileService.findOne(id) != null) {
            userProfileService.delete(id);
        }
        log.debug("API: User with id {} successfully deleted", id);
        return new ResponseEntity<>("API: User successfully deleted", HttpStatus.OK);
    }

    public UserProfileDTO getUserProfileById(long id) {

        UserProfile profile = userProfileService.findOne(id);

        return mapper.convertToUserProfileDTO(profile);
    }

    public UserProfileDTO getUserProfileByUsername(String username) {

        UserProfile profile = userProfileService.findByUsername(username);

        return mapper.convertToUserProfileDTO(profile);
    }

    public ResponseEntity<String> updateUser(UserProfileDTO profileDTO, BindingResult bindingResult)
            throws BindException {

        userProfileValidator.validate(profileDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            log.error("API: User can't be updated because of invalid data");
            throw new BindException(bindingResult);
        }

        log.debug("API: User successfully updated");
        userProfileService.update(profileDTO.getId(), mapper.convertToUserProfile(profileDTO));

        return new ResponseEntity<>("API: User successfully updated", HttpStatus.OK);
    }
}
