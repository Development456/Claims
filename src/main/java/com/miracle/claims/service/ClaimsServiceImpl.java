package com.miracle.claims.service;

import com.miracle.claims.beans.Claim;
import com.miracle.claims.repository.ClaimsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
//@Slf4j
public class ClaimsServiceImpl implements ClaimsService {

	@Autowired
	ClaimsRepository claimsRepository;
	
	@Autowired
	MongoOperations mongoOperations;

	@Autowired
	ClaimsSequenceGeneratorService claimsSeqGeneratorSvc;

	// get the list of all
	@Override
	public List<Claim> getAllClaims() {
		
		return claimsRepository.findAll();
	}
	
	
	//filter
	@Override
	public ResponseEntity<List<Claim>> getAllClaimsFilter(Claim claim, int page, int size, String sort) {
		
		
		
		Pageable pageable = PageRequest.of(page, size);
		
		Query query = new Query();
		query.with(pageable);
		
		List<Criteria> criteria = new ArrayList<>();
		
		if(claim.getClaimId() != null) {
			criteria.add(Criteria.where("claim_id").is(claim.getClaimId()));
		}
		if(claim.getFacilityId() != null) {
			criteria.add(Criteria.where("facility_id").is(claim.getFacilityId()));
		}
		
		if(claim.getDocumentType() != null) {
			criteria.add(Criteria.where("document_type").is(claim.getDocumentType()));
		}
		
		if(claim.getClaimedAmount() != null ) {
			criteria.add(Criteria.where("claimed_amount").is(claim.getClaimedAmount()));
		}
		
		if(claim.getClaimStatus() != null) {
			criteria.add(Criteria.where("claim_status").is(claim.getClaimStatus()));
		}
		
		if(claim.getClaimType() != null) {
			criteria.add(Criteria.where("claim_type").is(claim.getClaimType()));
		}
		
		if(claim.getCreatedDate() !=null ) {
			criteria.add(Criteria.where("create_date").is(claim.getCreatedDate()));
		}
		if(claim.getUserId() !=null ) {
			criteria.add(Criteria.where("user_id").is(claim.getUserId()));
		}
		
		query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
		
		List<Claim> filteredVals = mongoOperations.find(query, Claim.class);
		
		return new ResponseEntity<List<Claim>>(filteredVals, new HttpHeaders(), HttpStatus.OK);
	}

	// post
	@Override
	public ResponseEntity<Claim> createClaims(Claim claim) {
		claim.setServiceProviderClaimId(claimsSeqGeneratorSvc.generateSequence(Claim.SEQUENCE_NAME));
		Claim newClaim = claimsRepository.save(claim);
		return new ResponseEntity<Claim>(newClaim, new HttpHeaders(), HttpStatus.OK);
	}

	// put
	@Override
	public ResponseEntity<Claim> updateClaims(Long claimId, Claim claim) {
		try {
			Claim claims = claimsRepository.findById(claimId);
			System.out.println("this is service pro" + claims.getServiceProviderClaimId());
			// log.info("this is the output" + claims.getClaimId());
			claims.setClaimId(claim.getClaimId());
			claims.setFacilityId(claim.getFacilityId());
			claims.setDocumentType(claim.getDocumentType());
			claims.setClaimStatus(claim.getClaimStatus());
			claims.setPalletQuantity(claim.getPalletQuantity());
			claims.setClaimedAmount(claim.getClaimedAmount());
			claims.setClaimType(claim.getClaimType());
			claims.setPaidAmount(claim.getPaidAmount());
            claims.setCreatedDate(claim.getCreatedDate());
			claims.setCustomerId(claim.getCustomerId());
			claimsRepository.save(claims);
			return new ResponseEntity<Claim>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	// delete
	@Override
	public String deleteClaims(Long serviceProviderClaimId) {
		claimsRepository.deleteByServiceProviderClaimId(serviceProviderClaimId);
		return "claim deleted with id : " + serviceProviderClaimId;
	}

	@Override
	public Claim getClaim(long serviceProviderClaimId) {
		return claimsRepository.findByServiceProviderClaimId(serviceProviderClaimId);
	}
	@Override
	public ResponseEntity<List<Claim>> getFacilityClaim(String facilityId) {
		// TODO Auto-generated method stub
		List<Claim> list = new ArrayList<>();
		if(facilityId == null) {
			list.addAll(claimsRepository.findAll());
		}else {
			list.addAll(claimsRepository.findByFacilityId(facilityId));
		}		
		return new ResponseEntity<>(list,new HttpHeaders(), HttpStatus.OK); 

	}

	@Override
	public ResponseEntity<List<Claim>> getClaimsByStatus(String claimStatus) {
		List<Claim> list = new ArrayList<>(); 
		if(claimStatus == null) {
			list.addAll(claimsRepository.findAll());
		}else {
			list.addAll(claimsRepository.findByStatus(claimStatus));
		}
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<Claim>> getClaimsByType(String claimType) {
		List<Claim> list = new ArrayList<>();
		if(claimType == null) {
			list.addAll(claimsRepository.findAll());
		}
		else {
			list.addAll(claimsRepository.findByType(claimType));
		}	
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<Claim>> getClaimsByDocumentType(String documentType) {
		List<Claim> list = new ArrayList<>();
		if(documentType == null) {
			list.addAll(claimsRepository.findAll());
		}
		else {
			list.addAll(claimsRepository.findByDocType(documentType));
		}	
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
	}

	public Claim getClaimByCreator(String creatorId) {
		return claimsRepository.findByCreatorId(creatorId);
	}
	@Override
	public Claim getCustomerClaim(String claimId) {
		return claimsRepository.findByCustomerClaimId(claimId);
	}
	@Override
	public ResponseEntity<List<Claim>> getAllClaimsByStatus(){
		List<Claim> list = new ArrayList<>(claimsRepository.findClaimsByStatus());
		System.out.println(list);
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);

	}
	@Override
	public ResponseEntity<List<Claim>> getClaimsByCreateDate(String createDate) {
		List<Claim> list = new ArrayList<>();
		if(createDate == null) {
			list.addAll(claimsRepository.findAll());
		}
		else {
			claimsRepository.findByCreatedDate(createDate).forEach(list::add);
		}	
		return new ResponseEntity<List<Claim>>(list, new HttpHeaders(), HttpStatus.OK);
	}
//	@Override
//	public ResponseEntity<List<Claim>> getClaimsByClaimedAmountAndStatus(String claimedAmount, String claimStatus){
//		List<Claim> list = new ArrayList<>();
//		claimsRepository.findByClaimedAmountandStatus(claimedAmount,claimStatus);
//		return new ResponseEntity<List<Claim>>(list, new HttpHeaders(), HttpStatus.OK);
//	}

	public ResponseEntity<List<Claim>> getClaimsByClosedDate(String closedDate) {
		List<Claim> list = new ArrayList<>();
		if(closedDate == null) {	
			claimsRepository.findAll().forEach(list::add);
		}
		else {
			claimsRepository.findByClosedDate(closedDate).forEach(list::add);
		}	
		return new ResponseEntity<List<Claim>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@Override//find a way by query
	public ResponseEntity<List<Claim>> getAllMessagesPaginated(int start, int size) {
		ArrayList<Claim> paginatedMsg = new ArrayList<>(claimsRepository.findAll());
		return new ResponseEntity<>(paginatedMsg.subList(start, start + size), new HttpHeaders(), HttpStatus.OK);
	}

	public List<Map> getClaimsByDateRange(String startDate, String endDate) {
		List<Claim> claims = claimsRepository.findByDateRange(startDate, endDate);
		List<Claim> claimList = claimsRepository.findAll();
		HashMap<String, Integer> map = new HashMap<>();
		List<Map> claimStatusAndCount = new ArrayList<>();

//		Query query = new Query();
//
//		List<Criteria> criteria = new ArrayList<>();
//		for(Claim claim:claims){
//			if(claim.getClaimStatus() != null) {
//				criteria.add(Criteria.where("claim_status").is(claim.getClaimStatus()));
//			}
//		}
//		query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
//
//		return mongoOperations.find(query, Claim.class);
		if(startDate == null && endDate == null){
			for(Claim claim: claimList){
				map.put(claim.getClaimStatus(), map.getOrDefault(claim.getClaimStatus(),0)+1);
			}
		}
		else{
			for(Claim claim: claims){
			 	map.put(claim.getClaimStatus(), map.getOrDefault(claim.getClaimStatus(),0)+1);
			}
		}
		claimStatusAndCount.add(map);
		return claimStatusAndCount;
	}

	public int claimCount(){
		List<Claim> claim = claimsRepository.findAll();
		return claim.size();
	}

	public float totalClaimAmount(){
		float claimedAmount = 0;
		List<Claim> claims = claimsRepository.findAll();
		for(Claim claim:claims){
			claimedAmount += Float.parseFloat(claim.getClaimedAmount());
		}
		return claimedAmount;

	}
	public float totalPaidAmount(){
		float totalPaidAmount = 0;
		List<Claim> claims = claimsRepository.findAll();
		for(Claim claim:claims){
			totalPaidAmount += Float.parseFloat(claim.getPaidAmount());
		}
		return totalPaidAmount;

	}
}