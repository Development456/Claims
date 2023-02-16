package com.miracle.claims.service;


import com.miracle.claims.beans.Claim;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClaimsService {
	
public ResponseEntity<List<Claim>> getAllClaimsFilter(Claim claim, int page, int size, String sort);
	
	public ResponseEntity<List<Claim>> getAllClaimsByStatus();
	
	public ResponseEntity<Claim> createClaims(Claim claim);
	
	public ResponseEntity<Claim> updateClaims(Long claimId, Claim claim);
	
	public String deleteClaims(Long serviceProviderClaimId);
	
	public Claim getClaim(long serviceProviderClaimId);

	public ResponseEntity<List<Claim>> getClaimsByType(String claimType);

	public ResponseEntity<List<Claim>> getFacilityClaim(String facilityId);
	
	public ResponseEntity<List<Claim>> getClaimsByStatus(String claimStatus);

	public ResponseEntity<List<Claim>> getClaimsByDocumentType(String documentType);
	
	public Claim getCustomerClaim(String claimId);
	public Claim getClaimByCreator(String creatorId);
	public ResponseEntity<List<Claim>> getClaimsByCreateDate(String createDate);
	public ResponseEntity<List<Claim>> getClaimsByClosedDate(String closedDate);
	
	public ResponseEntity<List<Claim>> getAllMessagesPaginated(int start, int size);

	ResponseEntity<List<Claim>> getAllClaims();

	public int claimCount();
	public float totalClaimAmount();


//	public ResponseEntity<List<Claim>> getClaimsByClaimedAmountAndStatus(String claimedAmount, String claimStatus);
}
