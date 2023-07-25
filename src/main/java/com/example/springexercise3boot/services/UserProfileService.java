package com.example.springexercise3boot.services;

import com.example.springexercise3boot.models.user.UserProfile;
import com.example.springexercise3boot.repositories.UserProfilesRepository;
import com.example.springexercise3boot.util.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserProfileService {

    private final UserProfilesRepository userProfilesRepository;

    public List<UserProfile> findAll() {
        return userProfilesRepository.findAll();
    }

    public UserProfile findOne(long id) {
        Optional<UserProfile> foundUserProfile = userProfilesRepository.findById(id);
        return foundUserProfile.orElseThrow(() -> new UserNotFoundException("No user by ID: " + id));
    }

    public Optional<UserProfile> findByUsername(String username) {
        return userProfilesRepository.queryDistinctByUsername(username);
    }

    public Optional<UserProfile> findByEmail(String email) {
        return userProfilesRepository.queryDistinctByEmail(email);
    }

    @Transactional
    public void save(UserProfile profile) {
        userProfilesRepository.save(profile);
    }

    @Transactional
    public void update(long id, UserProfile updatedProfile) {
        updatedProfile.setId(id);
        userProfilesRepository.save(updatedProfile);
    }

    @Transactional
    public void delete(long id) {
        userProfilesRepository.deleteById(id);
    }
}
