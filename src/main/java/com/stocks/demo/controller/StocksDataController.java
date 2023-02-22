package com.stocks.demo.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.exceptions.CsvValidationException;
import com.stocks.demo.model.StockDetails;
import com.stocks.demo.service.StocksService;

@RestController
@RequestMapping("/api/stock-data")

public class StocksDataController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StocksService stockService;

	@PostMapping(value = "/bulk-insert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadData(@RequestParam("file") MultipartFile file)
			throws CsvValidationException, IOException {
		// save file data
		stockService.saveStocksFromFile(file);
		return ResponseEntity.ok("Upload successful");
	}

	@PostMapping("/")
	public ResponseEntity<Object> addStock(@RequestBody StockDetails stock) {
		// create record of stock data
		stockService.createStock(stock);
		return ResponseEntity.ok("sucess");

	}

	@GetMapping("/{stock}")
	public ResponseEntity<Object> getData(@PathVariable String stock) {
		// fetch list of records corressponding to stock passed in path variable
		List<StockDetails> list = stockService.getStockList(stock);
		return new ResponseEntity<>(list, HttpStatus.OK);

	}
}
