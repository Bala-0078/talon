package com.talon.integrationapi.service;

import com.talon.integrationapi.model.CustomerSession;
import com.talon.integrationapi.repository.CustomerSessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomerSessionService {
    private final CustomerSessionRepository repository;

    public CustomerSessionService(CustomerSessionRepository repository) {
        this.repository = repository;
    }

    public CustomerSession updateOrCreateSession(String integrationId, CustomerSession session) {
        Optional<CustomerSession> existing = repository.findByIntegrationId(integrationId);
        if (existing.isPresent()) {
            CustomerSession cs = existing.get();
            cs.setState(session.getState());
            cs.setProfileId(session.getProfileId());
            cs.setUpdated(LocalDateTime.now());
            cs.setCouponCodes(session.getCouponCodes());
            cs.setReferralCode(session.getReferralCode());
            cs.setTotal(session.getTotal());
            return repository.save(cs);
        } else {
            session.setIntegrationId(integrationId);
            session.setUpdated(LocalDateTime.now());
            return repository.save(session);
        }
    }

    public Optional<CustomerSession> getSession(String integrationId) {
        return repository.findByIntegrationId(integrationId);
    }
}
