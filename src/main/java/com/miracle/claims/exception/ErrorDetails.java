package com.miracle.claims.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "status", "message" })
@Data
public class ErrorDetails {

	private int status;

	private String message;

	public ErrorDetails() {
	}

	public ErrorDetails(int status, String message) {
		super();
		this.status = status;
		this.message = message;

	}
}
