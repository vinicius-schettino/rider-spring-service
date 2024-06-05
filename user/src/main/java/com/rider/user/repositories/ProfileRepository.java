package com.rider.user.repositories;

import com.rider.user.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID>{
}

