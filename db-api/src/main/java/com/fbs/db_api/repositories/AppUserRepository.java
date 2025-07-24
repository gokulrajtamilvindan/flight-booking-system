package com.fbs.db_api.repositories;

import com.fbs.db_api.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    public List<AppUser> findByUserType(String userType);
    public AppUser findByEmail(String email);
}
