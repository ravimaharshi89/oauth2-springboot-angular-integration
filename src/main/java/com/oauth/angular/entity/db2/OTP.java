package com.oauth.angular.entity.db2;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="OTP")
public class OTP implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@SequenceGenerator(name = "OTP_ID_GEN", sequenceName = "OTP_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OTP_ID_GEN")
	@Column(name = "OTP_ID")
	private Integer otpId;

	@Column(name = "OTP_TOKEN")
	private String otpToken;

	@Column(name = "OTP_CODE")
	private String otpCode;

	@Column(name = "ATTEMPTS")
	private Integer attempts;

	@Column(name = "MSISDN")
	private String msisdn;

	@Column(name = "APPLICATION")
	private String application;

	@Column(name = "GENERATED_ON")
	private Timestamp generatedOn;

	@Column(name = "ADDITIONAL_DATA")
	private String additionalData;

	public Integer getOtpId() {
		return otpId;
	}

	public void setOtpId(final Integer otpId) {
		this.otpId = otpId;
	}

	public String getOtpToken() {
		return otpToken;
	}

	public void setOtpToken(final String otpToken) {
		this.otpToken = otpToken;
	}

	public String getOtpCode() {
		return otpCode;
	}

	public void setOtpCode(final String otpCode) {
		this.otpCode = otpCode;
	}

	public Integer getAttempts() {
		return attempts;
	}

	public void setAttempts(final Integer attempts) {
		this.attempts = attempts;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(final String msisdn) {
		this.msisdn = msisdn;
	}

	public Timestamp getGeneratedOn() {
		return generatedOn;
	}

	public void setGeneratedOn(final Timestamp generatedOn) {
		this.generatedOn = generatedOn;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(final String application) {
		this.application = application;
	}

	public String getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(final String additionalData) {
		this.additionalData = additionalData;
	}

	@Override
	public String toString() {
		return "OTP [otpId=" + otpId + ", otpToken=" + otpToken + ", otpCode="
				+ otpCode + ", attempts=" + attempts + ", msisdn=" + msisdn
				+ ", application=" + application + ", generatedOn="
				+ generatedOn + ", additionalData=" + additionalData + "]";
	}
}
