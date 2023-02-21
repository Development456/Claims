// package com.miracle.claims;

// import com.miracle.claims.beans.Claim;
// import com.miracle.claims.repository.ClaimsRepository;
// import com.miracle.claims.service.ClaimsServiceImpl;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;

// import java.util.List;
// import java.util.stream.Collectors;
// import java.util.stream.Stream;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.context.junit4.SpringRunner;

// import com.miracle.claims.beans.Claim;
// import com.miracle.claims.repository.ClaimsRepository;
// import com.miracle.claims.service.ClaimsServiceImpl;

// @RunWith(SpringRunner.class)
// @SpringBootTest
// public class ClaimsApplicationTests {

// @Autowired
// private ClaimsServiceImpl service;

// @MockBean
// private ClaimsRepository repository;

// //test case for get all claims
// @Test
// public void getAllClaimsTest() {
// when(repository.findAll()).thenReturn(Stream
// 		.of(new Claim("639391cbc93a70679cbd8092", "2", null, 162592, "WAREHOUSE", "Closed", "950.00", 0, "950.00", "LA402100", "120", "inbound receipt", "19-DEC-2020", null, null, null, null, null)).collect(Collectors.toList()));
// assertEquals(1, service.getAllClaims());
// }

// //Test case to get claim by service provider claim id
// @Test
// public void getClaimByServiceProviderClaimId() {
// Long serviceProviderClaimId = (long) 162592;
// when(repository.findByServiceProviderClaimId(serviceProviderClaimId)).thenReturn((Claim) Stream
// .of(new Claim("639391cbc93a70679cbd8092", "2", null, 162592, "WAREHOUSE", "Closed", "950.00", 0, "950.00", "LA402100", "120", "inbound receipt", "19-DEC-2020", null, null, null, null, null)).collect(Collectors.toList()));
// assertEquals(1, service.getClaim(serviceProviderClaimId));
// }

// //test to get claim by facilityId
// @Test
// public void getFacilityClaimTest() {
// String facilityId = "PAH00000";
// when(repository.findByFacilityId(facilityId)).thenReturn((List<Claim>) Stream
// .of(new Claim("639391cbc93a70679cbd8092", "2", null, 162592, "WAREHOUSE", "Closed", "950.00", 0, "950.00", "LA402100", "120", "inbound receipt", "19-DEC-2020", null, null, null, null, null)).collect(Collectors.toList()));
// assertEquals(1, service.getFacilityClaim(facilityId));
// }

// //test to get claim by claim status
// @Test
// public void getClaimsByStatusTest()
// {
// String claimStatus = "Paid";
// when(repository.findByStatus(claimStatus)).thenReturn((List<Claim>) Stream
// .of(new Claim("639391cbc93a70679cbd8092", "2", null, 162592, "WAREHOUSE", "Closed", "950.00", 0, "950.00", "LA402100", "120", "inbound receipt", "19-DEC-2020", null, null, null, null, null)).collect(Collectors.toList()));
// assertEquals(1, service.getClaimsByStatus(claimStatus));
// }

// //test to get claim by type
// @Test
// public void getClaimsByTypeTest() {
// String claimType = "WAREHOUSE";
// when(repository.findByType(claimType)).thenReturn((List<Claim>) Stream
// .of(new Claim("639391cbc93a70679cbd8092", "2", null, 162592, "WAREHOUSE", "Closed", "950.00", 0, "950.00", "LA402100", "120", "inbound receipt", "19-DEC-2020", null, null, null, null, null)).collect(Collectors.toList()));
// assertEquals(1, service.getClaimsByType(claimType));

// }

// //test to get claims by document type
// @Test
// public void getClaimsByDocumentTypeTest() {
// String documentType = "inbound receipt";
// when(repository.findByDocType(documentType)).thenReturn((List<Claim>) Stream
// .of(new Claim("639391cbc93a70679cbd8092", "2", null, 162592, "WAREHOUSE", "Closed", "950.00", 0, "950.00", "LA402100", "120", "inbound receipt", "19-DEC-2020", null, null, null, null, null)).collect(Collectors.toList()));
// assertEquals(1, service.getClaimsByDocumentType(documentType));
// }

// //test to get claim by creator id
// @Test
// public void getClaimByCreatorTest() {
// String creatorId ="";
// when(repository.findByCreatorId(creatorId)).thenReturn((Claim) Stream
// .of(new Claim("639391cbc93a70679cbd8092", "2", null, 162592, "WAREHOUSE", "Closed", "950.00", 0, "950.00", "LA402100", "120", "inbound receipt", "19-DEC-2020", null, null, null, null, null)).collect(Collectors.toList()));
// assertEquals(1, service.getClaimByCreator(creatorId));
// }

// //test to get claim by claim id
// @Test
// public void getCustomerClaimTest() {
// String claimId = "10";
// when(repository.findByCustomerClaimId(claimId)).thenReturn((Claim) Stream
// 		.of(new Claim("639391cbc93a70679cbd8092", "2", null, 162592, "WAREHOUSE", "Closed", "950.00", 0, "950.00", "LA402100", "120", "inbound receipt", "19-DEC-2020", null, null, null, null, null)).collect(Collectors.toList()));
// 		assertEquals(1, service.getCustomerClaim(claimId));
// 	}


// }
