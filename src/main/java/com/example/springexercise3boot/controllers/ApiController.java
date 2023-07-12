package com.example.springexercise3boot.controllers;

import com.example.springexercise3boot.dto.UserProfileDTO;
import com.example.springexercise3boot.services.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
@Slf4j
public class ApiController {

    private final AdminService adminService;

    @GetMapping("users")
    public List<UserProfileDTO> getUsers() {
        log.debug("API: requesting list of UserProfiles");

        return adminService.getUsers();
    }

    @PostMapping(value = "users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addUser(@RequestBody @Valid UserProfileDTO profileDTO, BindingResult bindingResult)
            throws BindException {
        log.debug("API: Attempt inserting user into database. Login: {}, Role: {}, Name: {}, Email: {}",
                profileDTO.getUsername(), profileDTO.getRole(), profileDTO.getName(), profileDTO.getEmail());

        return adminService.addUser(profileDTO, bindingResult);
    }

    @DeleteMapping("deleteUser")
    public ResponseEntity<String> deleteUserById(@RequestParam("id") long id) {
        log.debug("API: Attempt removing user from database, ID: {}", id);

        return adminService.deleteUserById(id);
    }

    @GetMapping("users/{id}")
    public UserProfileDTO getUserProfileById(@PathVariable("id") long id) {
        log.debug("API: UserProfile with ID: {} requested from DB", id);

        return adminService.getUserProfileById(id);
    }

    @GetMapping("user/{username}")
    public UserProfileDTO getUserProfileByUsername(@PathVariable("username") String username) {
        log.debug("API: UserProfile with username {} requested from DB", username);

        return adminService.getUserProfileByUsername(username);
    }

    @PostMapping("updateUser")
    public ResponseEntity<String> updateUser(@RequestBody @Valid UserProfileDTO profileDTO,
                                             BindingResult bindingResult) throws BindException {
        log.debug("API: Attempt to update user in database. Name: {}, Email: {}",
                profileDTO.getName(), profileDTO.getEmail());

        return adminService.updateUser(profileDTO, bindingResult);
    }
}
