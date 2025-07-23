package com.talon.integrationapi.controller;

import com.talon.integrationapi.model.CustomerSession;
import com.talon.integrationapi.service.CustomerSessionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/customer_sessions")
public class CustomerSessionController {
    private final CustomerSessionService service;

    public CustomerSessionController(CustomerSessionService service) {
        this.service = service;
    }

    @PutMapping("/{customerSessionId}")
    public ResponseEntity<CustomerSession> updateCustomerSession(
            @PathVariable String customerSessionId,
            @Valid @RequestBody CustomerSession session) {
        CustomerSession updated = service.updateOrCreateSession(customerSessionId, session);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{customerSessionId}")
    public ResponseEntity<CustomerSession> getCustomerSession(@PathVariable String customerSessionId) {
        return service.getSession(customerSessionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
