package com.talon.integration.controller;

import com.talon.integration.service.TalonOneIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/customer-sessions")
public class CustomerSessionController {
    @Autowired
    private TalonOneIntegrationService talonService;

    /**
     * Sync or update a customer session (calls /v2/customer_sessions/{customerSessionId} in Talon.One)
     */
    @PutMapping("/{customerSessionId}")
    public ResponseEntity<String> updateCustomerSession(
            @PathVariable String customerSessionId,
            @RequestBody Map<String, Object> sessionPayload,
            @RequestParam(defaultValue = "false") boolean dry
    ) {
        return talonService.syncCustomerSession(customerSessionId, sessionPayload, dry);
    }

    // Additional endpoints (GET, POST returns, etc.) can be implemented similarly
}
