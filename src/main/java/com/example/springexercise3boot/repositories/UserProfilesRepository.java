package com.example.springexercise3boot.repositories;

import com.example.springexercise3boot.models.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfilesRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> queryDistinctByUsername(String username);

    Optional<UserProfile> queryDistinctByEmail(String email);
}
