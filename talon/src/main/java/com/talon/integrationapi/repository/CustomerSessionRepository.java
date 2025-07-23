package com.talon.integrationapi.repository;

import com.talon.integrationapi.model.CustomerSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerSessionRepository extends JpaRepository<CustomerSession, Long> {
    Optional<CustomerSession> findByIntegrationId(String integrationId);
}
