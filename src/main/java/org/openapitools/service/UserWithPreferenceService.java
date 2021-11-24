package org.openapitools.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.User;
import org.openapitools.model.UserWithPreferences;
import org.openapitools.repository.UserWithPreferenceRepository;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class UserWithPreferenceService {
    private final UserWithPreferenceRepository userWithPreferenceRepository;

    public UserWithPreferences create(User user) {
        UserWithPreferences userwithpreferences = new UserWithPreferences(
                user.getEmail(), user.getPassword(), "Budapest", 10, "", "");
        log.debug("User create(User user): {}", user.toString());
        return userWithPreferenceRepository.save(userwithpreferences);
    }
    public UserWithPreferences findByToken(String token){
        return userWithPreferenceRepository.findByToken(token);
    }

    public UserWithPreferences findByEmail(String email){
        return userWithPreferenceRepository.findByEmail(email);
    }
    public void update(UserWithPreferences user){ userWithPreferenceRepository.save(user); };
}
