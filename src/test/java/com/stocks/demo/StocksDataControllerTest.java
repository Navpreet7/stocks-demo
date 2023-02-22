package com.stocks.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StocksDataControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	void shouldCreateNewStock() {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		//Create the request body
		HttpEntity<String> requestEntity = new HttpEntity<>("""
				      {
				  "quarter":"1",
				  "stock":"kmsugar",
				  "date":"1/14/2011",
				  "open":"$16.71",
				  "high":"$16.71",
				  "low":"$15.64",
				  "close":"$15.97",
				  "volume":"242963398",
				  "percentChangePrice":"-4.42849",
				  "percentChangeVolumeOverLastWk":"1.380223028",
				  "previousWeeksVolume":"239655616",
				  "nextWeeksOpen":"$16.19",
				  "nextWeeksClose":"$15.79",
				  "percentChangeNextWeeksPrice":"-2.47066",
				  "daysToNextDividend":"19",
				  "percentReturnNextDividend":"0.187852"
				}
				""", requestHeaders);
		// Send the POST request to the endpoint
		ResponseEntity<Void> response = this.testRestTemplate.exchange("/api/stock-data/", HttpMethod.POST,
				requestEntity, Void.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void shouldFetchStock() {

		HttpHeaders requestHeaders = new HttpHeaders();

		HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);

		ResponseEntity<Void> response = this.testRestTemplate.exchange("/api/stock-data/AA", HttpMethod.GET,
				requestEntity, Void.class);
		// Verify the response
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void shouldPassCSVData() {
		
		URL path = this.getClass().getResource("csv.data");
		File csvFile = new File(path.getFile());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		// Create the request body
		FileSystemResource fileResource = new FileSystemResource(csvFile);
		MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
		formData.add("file", fileResource);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(formData, headers);

		// Send the POST request to the endpoint
		ResponseEntity<String> responseEntity = this.testRestTemplate.exchange("/api/stock-data/bulk-insert",
				HttpMethod.POST, requestEntity, String.class);

		// Verify the response
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Upload successful", responseEntity.getBody());
	}

}