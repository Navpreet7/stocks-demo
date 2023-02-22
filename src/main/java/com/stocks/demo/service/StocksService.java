package com.stocks.demo.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.exceptions.CsvValidationException;
import com.stocks.demo.model.StockDetails;
import com.stocks.demo.repo.StocksRepository;
import com.stocks.demo.utils.CsvParser;

@Service
@Transactional
public class StocksService {
	@Autowired
	private StocksRepository stocksRepository;
	private final static Logger logger = LoggerFactory.getLogger(StocksService.class);

	/**
	 * Method to fetch stock list from db
	 * 
	 * @param name
	 * @return stock list
	 */
	public List<StockDetails> getStockList(String name) {
		logger.info("fetching records for stock: "+name);
		List<StockDetails> stockList = stocksRepository.findByStock(name);
		return stockList;
	}

	/**
	 * Method to create a stock entry in db
	 * 
	 * @param stock
	 */
	public void createStock(StockDetails stock) {
		logger.info("saving a stock record db");
		stocksRepository.save(stock);
	}

	/**
	 * Method to store csv file data in db
	 * 
	 * @param file
	 * @throws CsvValidationException
	 * @throws IOException
	 */
	
	public void saveStocksFromFile(MultipartFile file) throws CsvValidationException, IOException {
		logger.info("uploading csv stock file: "+ file.getName());
		CsvParser parser = CsvParser.getInstance();
		List<StockDetails> stockList = parser.parseCsvFile(file);
		stocksRepository.saveAll(stockList);
	}

}
