package com.miracle.claims.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Service
// @Slf4j
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

	// filter
	@Override
	public ResponseEntity<List<Claim>> getAllClaimsFilter(Claim claim, int page, int size, String sort) {

		Pageable pageable = PageRequest.of(page, size);

		Query query = new Query();
		query.with(pageable);

		List<Criteria> criteria = new ArrayList<>();

		if (claim.getClaimId() != null) {
			criteria.add(Criteria.where("claim_id").is(claim.getClaimId()));
		}
		if (claim.getFacilityId() != null) {
			criteria.add(Criteria.where("facility_id").is(claim.getFacilityId()));
		}

		if (claim.getDocumentType() != null) {
			criteria.add(Criteria.where("document_type").is(claim.getDocumentType()));
		}

		if (claim.getClaimedAmount() != null) {
			criteria.add(Criteria.where("claimed_amount").is(claim.getClaimedAmount()));
		}

		if (claim.getClaimStatus() != null) {
			criteria.add(Criteria.where("claim_status").is(claim.getClaimStatus()));
		}

		if (claim.getClaimType() != null) {
			criteria.add(Criteria.where("claim_type").is(claim.getClaimType()));
		}

		if (claim.getCreatedDate() != null) {
			criteria.add(Criteria.where("create_date").is(claim.getCreatedDate()));
		}
		if (claim.getUserId() != null) {
			criteria.add(Criteria.where("user_id").is(claim.getUserId()));
		}

		query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));

		List<Claim> filteredVals = mongoOperations.find(query, Claim.class);

		return new ResponseEntity<>(filteredVals, new HttpHeaders(), HttpStatus.OK);
	}

	// post
	@Override
	public ResponseEntity<Claim> createClaims(Claim claim) {
		claim.setServiceProviderClaimId(claimsSeqGeneratorSvc.generateSequence(Claim.SEQUENCE_NAME));
		Claim newClaim = claimsRepository.save(claim);
		return new ResponseEntity<>(newClaim, new HttpHeaders(), HttpStatus.OK);
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
			return new ResponseEntity<>(HttpStatus.OK);
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
		List<Claim> list = new ArrayList<>();
		if (facilityId == null) {
			list.addAll(claimsRepository.findAll());
		} else {
			list.addAll(claimsRepository.findByFacilityId(facilityId));
		}
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<List<Claim>> getClaimsByStatus(String claimStatus) {
		List<Claim> list = new ArrayList<>();
		if (claimStatus == null) {
			list.addAll(claimsRepository.findAll());
		} else {
			list.addAll(claimsRepository.findByStatus(claimStatus));
		}
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Claim>> getClaimsByType(String claimType) {
		List<Claim> list = new ArrayList<>();
		if (claimType == null) {
			list.addAll(claimsRepository.findAll());
		} else {
			list.addAll(claimsRepository.findByType(claimType));
		}
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Claim>> getClaimsByDocumentType(String documentType) {
		List<Claim> list = new ArrayList<>();
		if (documentType == null) {
			list.addAll(claimsRepository.findAll());
		} else {
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
	public ResponseEntity<List<Claim>> getAllClaimsByStatus() {
		List<Claim> list = new ArrayList<>(claimsRepository.findClaimsByStatus());
//		log.info(list);
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<List<Claim>> getClaimsByCreateDate(String createDate) {
		List<Claim> list = new ArrayList<>();
		if (createDate == null) {
			list.addAll(claimsRepository.findAll());
		} else {
			list.addAll(claimsRepository.findByCreatedDate(createDate));
		}
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
	}


	public ResponseEntity<List<Claim>> getClaimsByClosedDate(String closedDate) {
		List<Claim> list = new ArrayList<>();
		if (closedDate == null) {
			list.addAll(claimsRepository.findAll());
		} else {
			list.addAll(claimsRepository.findByClosedDate(closedDate));
		}
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@Override // find a way by query
	public ResponseEntity<List<Claim>> getAllMessagesPaginated(int start, int size) {
		ArrayList<Claim> paginatedMsg = new ArrayList<>(claimsRepository.findAll());
		return new ResponseEntity<>(paginatedMsg.subList(start, start + size), new HttpHeaders(), HttpStatus.OK);
	}

//	public List<Map> getClaimsByDateRange(String startDate, String endDate) {
//		HashMap<String, Integer> map = new HashMap<>();
//		List<Map> claimStatusAndCount = new ArrayList<>();
//		if (startDate != null && endDate != null) {
//			List<Claim> claims = claimsRepository.findByDateRange(startDate, endDate);
//			for (Claim claim : claims) {
//				map.put(claim.getClaimStatus(), map.getOrDefault(claim.getClaimStatus(), 0) + 1);
//			}
//		}else {
//			List<Claim> claimList = claimsRepository.findAll();
//			for (Claim claim : claimList) {
//				map.put(claim.getClaimStatus(), map.getOrDefault(claim.getClaimStatus(), 0) + 1);
//			}
//
//		}
//		claimStatusAndCount.add(map);
//		return claimStatusAndCount;
//	}
	public List<Map> getClaimsByDateRange(String startDate, String endDate) {

		List<Claim> claims;
		if (startDate != null && endDate != null) {
			claims = claimsRepository.findByDateRange(startDate, endDate);
		} else {
			claims = claimsRepository.findAll();
		}

		Map<String, Integer> claimStatusCounts = new HashMap<>();
		for (Claim claim : claims) {
			claimStatusCounts.put(claim.getClaimStatus(), claimStatusCounts.getOrDefault(claim.getClaimStatus(), 0) + 1);
		}

		List<Map> claimStatusAndCount = new ArrayList<>();
		claimStatusAndCount.add(claimStatusCounts);

		return claimStatusAndCount;
	}

//	@Override
//	public List<Map>getBarChartDetailsByDateRange(String startDate, String endDate) {
//		HashMap<String, Integer> mapOpen = new HashMap<>();
//		HashMap<String, Integer> mapClosed = new HashMap<>();
//		List<Map> claimAmountCount = new ArrayList<>();
//
//		if (startDate != null && endDate != null) {
//			List<Claim> claims = claimsRepository.findByDateRange(startDate, endDate);
//			for (Claim claim : claims) {
//				if (Objects.equals(claim.getClaimStatus(), "Open")) {
//					mapOpen.put(claim.getMasterAccount(), Integer.parseInt(claim.getClaimedAmount()));
//				} else if (Objects.equals(claim.getClaimStatus(), "Closed")) {
//					mapClosed.put(claim.getMasterAccount(), Integer.parseInt(claim.getClaimedAmount()));
//				}
//			}
//		} else {
//			List<Claim> claimList = claimsRepository.findAll();
//			for (Claim claim : claimList) {
//				if(Objects.equals(claim.getClaimStatus(), "Open")){
//					mapOpen.put(claim.getMasterAccount(), Integer.parseInt(claim.getClaimedAmount()));
//				} else if (Objects.equals(claim.getClaimStatus(), "Closed")) {
//					mapClosed.put(claim.getMasterAccount(), Integer.parseInt(claim.getClaimedAmount()));
//				}
//			}
//		}
//		if(claimsRepository.findByClaimStatus("Closed")){
//			Map<String,Integer> sortedClosedMap = mapClosed.entrySet()
//					.stream()
//					.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//					.collect(toMap(Map.Entry::getKey, Map.Entry::getValue,(e1,e2)->e2,LinkedHashMap::new));
//			claimAmountCount.add(sortedClosedMap);
//		}else if(claimsRepository.findByClaimStatus("Open")){
//			Map<String,Integer> sortedOpenMap = mapOpen.entrySet()
//					.stream()
//					.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//					.collect(toMap(Map.Entry::getKey, Map.Entry::getValue,(e1,e2)->e2,LinkedHashMap::new));
//			claimAmountCount.add(sortedOpenMap);
//		}
//		return claimAmountCount;
//	}
	@Override
	public List<Map> getBarChartDetailsByDateRange(String startDate, String endDate) {
		List<Claim> claims = (startDate != null && endDate != null) ?
				claimsRepository.findByDateRange(startDate, endDate) :
				claimsRepository.findAll();

		Map<String, Float> mapOpen = new HashMap<>();
		Map<String, Float> mapClosed = new HashMap<>();

		for (Claim claim : claims) {
			Map<String, Float> map;
			if (Objects.equals(claim.getClaimStatus(), "Open")) {
				map = mapOpen;
			} else if (Objects.equals(claim.getClaimStatus(), "Closed")) {
				map = mapClosed;
			} else {
				continue; // skip invalid claim status
			}
			map.put(claim.getMasterAccount(), Float.parseFloat(claim.getClaimedAmount()));
		}

		List<Map> claimAmountCount = new ArrayList<>();
		if (!mapClosed.isEmpty()) {
			Map<String, Float> sortedClosedMap = sortMapByValue(mapClosed);
			claimAmountCount.add(sortedClosedMap);
		}
		if (!mapOpen.isEmpty()) {
			Map<String, Float> sortedOpenMap = sortMapByValue(mapOpen);
			claimAmountCount.add(sortedOpenMap);
		}
		return claimAmountCount;
	}

	private Map<String, Float> sortMapByValue(Map<String, Float> map) {
		return map.entrySet()
				.stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
	}
	public int claimCount() {
		List<Claim> claim = claimsRepository.findAll();
		return claim.size();
	}

	public float totalClaimAmount() {
		float claimedAmount = 0;
		List<Claim> claims = claimsRepository.findAll();
		for (Claim claim : claims) {
			claimedAmount += Float.parseFloat(claim.getClaimedAmount());
		}
		return claimedAmount;

	}

	public float totalPaidAmount() {
		float totalPaidAmount = 0;
		List<Claim> claims = claimsRepository.findAll();
		for (Claim claim : claims) {
			totalPaidAmount += Float.parseFloat(claim.getPaidAmount());
		}
		return totalPaidAmount;

	}

	// Kafka Consumer
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(Claim message) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			String jsonString = mapper.writeValueAsString(message);
			System.out.println("Json message received using Kafka listener " + jsonString);

			// claim.setServiceProviderClaimId(claimsSeqGeneratorSvc.generateSequence(Claim.SEQUENCE_NAME));
			claimsRepository.save(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}