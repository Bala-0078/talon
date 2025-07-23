package com.talon.integration.controller;

import com.talon.integration.service.TalonIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TalonIntegrationController {
    @Autowired
    private TalonIntegrationService talonService;

    @PutMapping("/customer-sessions/{customerSessionId}")
    public ResponseEntity<String> updateCustomerSession(@PathVariable String customerSessionId, @RequestBody String body) {
        return talonService.forwardPut("/v2/customer_sessions/" + customerSessionId, body);
    }

    @GetMapping("/customer-sessions/{customerSessionId}")
    public ResponseEntity<String> getCustomerSession(@PathVariable String customerSessionId) {
        return talonService.forwardGet("/v2/customer_sessions/" + customerSessionId);
    }

    @PostMapping("/customer-sessions/{customerSessionId}/returns")
    public ResponseEntity<String> returnCartItems(@PathVariable String customerSessionId, @RequestBody String body) {
        return talonService.forwardPost("/v2/customer_sessions/" + customerSessionId + "/returns", body);
    }

    @PutMapping("/customer-sessions/{customerSessionId}/reopen")
    public ResponseEntity<String> reopenCustomerSession(@PathVariable String customerSessionId) {
        return talonService.forwardPut("/v2/customer_sessions/" + customerSessionId + "/reopen", "{}");
    }

    @PutMapping("/customer-profiles/{integrationId}")
    public ResponseEntity<String> updateCustomerProfile(@PathVariable String integrationId, @RequestBody String body) {
        return talonService.forwardPut("/v2/customer_profiles/" + integrationId, body);
    }

    @GetMapping("/customer-profiles/{integrationId}/inventory")
    public ResponseEntity<String> getCustomerInventory(@PathVariable String integrationId) {
        return talonService.forwardGet("/v1/customer_profiles/" + integrationId + "/inventory");
    }

    @GetMapping("/loyalty-programs/{loyaltyProgramId}/profile/{integrationId}/points")
    public ResponseEntity<String> getLoyaltyProgramProfilePoints(@PathVariable String loyaltyProgramId, @PathVariable String integrationId) {
        return talonService.forwardGet("/v1/loyalty_programs/" + loyaltyProgramId + "/profile/" + integrationId + "/points");
    }

    @PostMapping("/coupon-reservations/{couponValue}")
    public ResponseEntity<String> createCouponReservation(@PathVariable String couponValue, @RequestBody String body) {
        return talonService.forwardPost("/v1/coupon_reservations/" + couponValue, body);
    }
}
