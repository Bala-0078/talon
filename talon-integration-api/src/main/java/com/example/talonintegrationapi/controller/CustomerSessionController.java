package com.example.talonintegrationapi.controller;

import com.example.talonintegrationapi.model.CustomerSession;
import com.example.talonintegrationapi.service.CustomerSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v2/customer_sessions")
public class CustomerSessionController {
    private final CustomerSessionService service;
    @Autowired
    public CustomerSessionController(CustomerSessionService service) {
        this.service = service;
    }
    @PutMapping("/{customerSessionId}")
    public ResponseEntity<CustomerSession> updateCustomerSession(
            @PathVariable String customerSessionId,
            @RequestBody CustomerSession session) {
        CustomerSession updated = service.updateOrCreateSession(customerSessionId, session);
        return ResponseEntity.ok(updated);
    }
    @GetMapping("/{customerSessionId}")
    public ResponseEntity<CustomerSession> getCustomerSession(@PathVariable String customerSessionId) {
        Optional<CustomerSession> session = service.getSession(customerSessionId);
        return session.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
