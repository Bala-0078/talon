package com.example.talonintegrationapi.service;

import com.example.talonintegrationapi.model.CustomerSession;
import com.example.talonintegrationapi.repository.CustomerSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomerSessionService {
    private final CustomerSessionRepository repository;
    @Autowired
    public CustomerSessionService(CustomerSessionRepository repository) {
        this.repository = repository;
    }
    public CustomerSession updateOrCreateSession(String sessionId, CustomerSession session) {
        Optional<CustomerSession> existing = repository.findBySessionId(sessionId);
        if (existing.isPresent()) {
            CustomerSession s = existing.get();
            s.setProfileId(session.getProfileId());
            s.setState(session.getState());
            s.setUpdated(LocalDateTime.now());
            s.setTotal(session.getTotal());
            s.setCouponCodes(session.getCouponCodes());
            s.setReferralCode(session.getReferralCode());
            s.setCartItemsJson(session.getCartItemsJson());
            s.setAttributesJson(session.getAttributesJson());
            return repository.save(s);
        } else {
            session.setSessionId(sessionId);
            session.setUpdated(LocalDateTime.now());
            return repository.save(session);
        }
    }
    public Optional<CustomerSession> getSession(String sessionId) {
        return repository.findBySessionId(sessionId);
    }
}
