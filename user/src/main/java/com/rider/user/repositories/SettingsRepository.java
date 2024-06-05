package com.rider.user.repositories;

import com.rider.user.entities.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SettingsRepository extends JpaRepository<Settings, UUID> {
}
