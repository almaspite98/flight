package org.openapitools.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.openapitools.model.Preferences;
import org.openapitools.model.User;
import org.openapitools.model.UserWithPreferences;
import org.openapitools.repository.UserWithPreferenceRepository;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class UserWithPreferenceService {
    private final UserWithPreferenceRepository userWithPreferenceRepository;

    public void getAdminPage(){

    }

    public UserWithPreferences create(User user) {
        System.out.println("Register user: " + user.getEmail() + ", " + user.getPassword());
        UserWithPreferences userwithpreferences = findByEmail(user.getEmail());

        // if user already exists
        if (userwithpreferences != null) {
            throw new IllegalArgumentException("User already exists");
        }
        System.out.println("Registering new user");

        UserWithPreferences newUserWithPreferences = new UserWithPreferences(
                user.getEmail(), user.getPassword(), "Budapest", 10, "", UUID.randomUUID().toString());
        log.debug("User create(User user): {}", user.toString());
        return userWithPreferenceRepository.save(newUserWithPreferences);
    }

    public Preferences getPreferences(String token){
        UserWithPreferences user = findByToken(token);
        return new Preferences(user.getDeparture(), user.getTransferTime(), user.getAirline());
    }

    public UserWithPreferences setPreferences(Preferences preferences, String token){
        System.out.println("Token: " + token);
        UserWithPreferences user = findByToken(token);

        System.out.println("User is " + user.getEmail());

        user.setAirline(preferences.getAirline());
        user.setDeparture(preferences.getDeparture());
        user.setTransferTime(preferences.getTransferTime());

        return userWithPreferenceRepository.save(user);
    }

    @SneakyThrows
    public UserWithPreferences login(User user){
        System.out.println("Login user: " + user.getEmail() + ", " + user.getPassword());
        UserWithPreferences userwithpreferences = findByEmail(user.getEmail());
        if(userwithpreferences == null)
            throw new NoSuchElementException("User with given email doesnt exist. Please register");
        System.out.println("User credentials: " + user.getEmail() + ", " + user.getPassword());
        // if password is correct
        if (userwithpreferences.getPassword().equals(user.getPassword())) {
            // generate token
            String token = UUID.randomUUID().toString();
            System.out.println("Generated token: " + token);
            userwithpreferences.setToken(token);
            return update(userwithpreferences);
        } else
            throw new IllegalArgumentException("Wrong password");
    }

    @SneakyThrows
    public UserWithPreferences findByToken(String token) {
        var user = userWithPreferenceRepository.findByToken(token);
        // if invalid token
        if (user == null) {
            throw new AuthenticationException("Invalid user token " + token);
        }
        return user;
    }

    public UserWithPreferences findByEmail(String email) {
        return userWithPreferenceRepository.findByEmail(email);
    }

    public UserWithPreferences update(UserWithPreferences user) {
        return userWithPreferenceRepository.save(user);
    }

    ;
}
