package com.example.talonintegrationapi.repository;

import com.example.talonintegrationapi.model.CustomerSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerSessionRepository extends JpaRepository<CustomerSession, Long> {
    Optional<CustomerSession> findBySessionId(String sessionId);
}
