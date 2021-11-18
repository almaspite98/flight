package org.openapitools.repository;

import org.openapitools.model.User;
import org.openapitools.model.UserWithPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWithPreferenceRepository extends JpaRepository<UserWithPreferences, Integer>, JpaSpecificationExecutor<UserWithPreferences> {
    public UserWithPreferences findByToken(String token);
    public UserWithPreferences findByEmail(String email);
}
