package com.oauth.angular.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oauth.angular.repo.db2.GatewayRepository;

@Service
public class GatewayService {

	@Autowired
	private GatewayRepository gatewayRepository;
	
	public String getOtpforMsisdn(String msisdn) {
		return gatewayRepository.getOtpByMsisdn(msisdn);
	}

	public String clearOtpAttempts(String msisdn) {
		return gatewayRepository.clearOtpAttempts(msisdn);
		
	}
}
