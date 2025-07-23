package com.talonone.integrationapi.controller;

import com.talonone.integrationapi.model.CustomerSessionRequest;
import com.talonone.integrationapi.model.CustomerSessionResponse;
import com.talonone.integrationapi.service.CustomerSessionService;
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
    public ResponseEntity<CustomerSessionResponse> updateCustomerSession(
            @PathVariable String customerSessionId,
            @Valid @RequestBody CustomerSessionRequest request
    ) {
        return ResponseEntity.ok(service.updateSession(customerSessionId, request));
    }

    @GetMapping("/{customerSessionId}")
    public ResponseEntity<CustomerSessionResponse> getCustomerSession(
            @PathVariable String customerSessionId
    ) {
        return ResponseEntity.ok(service.getSession(customerSessionId));
    }
}
