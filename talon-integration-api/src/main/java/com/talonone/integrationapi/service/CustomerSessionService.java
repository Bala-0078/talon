package com.talonone.integrationapi.service;

import com.talonone.integrationapi.model.CustomerSessionRequest;
import com.talonone.integrationapi.model.CustomerSessionResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CustomerSessionService {
    // In-memory store for demo purposes
    private final Map<String, CustomerSessionResponse> sessions = new ConcurrentHashMap<>();

    public CustomerSessionResponse updateSession(String sessionId, CustomerSessionRequest request) {
        CustomerSessionResponse resp = new CustomerSessionResponse();
        resp.setCustomerSessionId(sessionId);
        resp.setProfileId(request.getProfileId());
        resp.setState(request.getState());
        resp.setCartItems(request.getCartItems());
        resp.setAttributes(request.getAttributes());
        sessions.put(sessionId, resp);
        return resp;
    }

    public CustomerSessionResponse getSession(String sessionId) {
        CustomerSessionResponse resp = sessions.get(sessionId);
        if (resp == null) throw new RuntimeException("Session not found");
        return resp;
    }
}
