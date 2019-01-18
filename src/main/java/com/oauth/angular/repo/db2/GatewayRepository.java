package com.oauth.angular.repo.db2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oauth.angular.entity.db2.OTP;

@Repository
public interface GatewayRepository extends JpaRepository<OTP, Long> {

	@Query(value = "select otp_code from otp where msisdn =:msisdn order by created_date desc fetch first 1 rows only", nativeQuery = true)
	String getOtpByMsisdn(@Param ("msisdn") String msisdn);

	@Query(value = "delete otp where msisdn =:msisdn", nativeQuery = true)
	String clearOtpAttempts(@Param ("msisdn") String msisdn);

}
