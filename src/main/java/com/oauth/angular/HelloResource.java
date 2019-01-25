package com.oauth.angular;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.angular.service.GatewayService;

@RestController
@RequestMapping("/rest/hello")
public class HelloResource {

	@Autowired
	private GatewayService gatewayService;
	
    @GetMapping("/principal")
    public Principal user(Principal principal) {
        return principal;
    }
    @GetMapping
    public String hello() {
        return "Hello World";
    }

    @Profile("dev")
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/fetch-otp/{msisdn}")
    public String getOtp(@PathVariable("msisdn") String msisdn) {
        return gatewayService.getOtpforMsisdn(msisdn);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/clear-otp-attempts/{msisdn}")
    public String clearOtpAttempts(@PathVariable("msisdn") String msisdn) {
        return gatewayService.clearOtpAttempts(msisdn);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/clear-otp-attempts/{msisdn}")
    public String sendOtp(@PathVariable("msisdn") String msisdn) {
        return gatewayService.clearOtpAttempts(msisdn);
    }
    
}
