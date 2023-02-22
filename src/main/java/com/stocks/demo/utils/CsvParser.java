package com.stocks.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.stocks.demo.model.StockDetails;

public class CsvParser {

	private static CsvParser instance;

	/**
	 * Method to get singelton instance of csvparser
	 * 
	 * @return instance
	 */
	public static CsvParser getInstance() {
		if (instance == null) {
			synchronized (CsvParser.class) {
				instance = new CsvParser();
			}
		}
		return instance;
	}

	/**
	 * Map to parse the file headers correctly to model class.
	 */
	private final Map<String, String> columnMappings = Map.ofEntries(

			Map.entry("percent_change_price", "percentChangePrice"),

			Map.entry("percent_change_volume_over_last_wk", "percentChangeVolumeOverLastWk"),

			Map.entry("previous_weeks_volume", "previousWeeksVolume"),

			Map.entry("next_weeks_open", "nextWeeksOpen"),

			Map.entry("next_weeks_close", "nextWeeksClose"),

			Map.entry("percent_change_next_weeks_price", "percentChangeNextWeeksPrice"),

			Map.entry("days_to_next_dividend", "daysToNextDividend"),

			Map.entry("percent_return_next_dividend", "percentReturnNextDividend"), Map.entry("quarter", "quarter"),

			Map.entry("stock", "stock"),

			Map.entry("date", "date"),

			Map.entry("open", "open"),

			Map.entry("high", "high"),

			Map.entry("low", "low"),

			Map.entry("close", "close"),

			Map.entry("volume", "volume"));

	/**
	 * Method to parse csv file to list of objects
	 * 
	 * @param file
	 * @return list of objects
	 * @throws IOException
	 */

	public List<StockDetails> parseCsvFile(MultipartFile file) throws IOException {

		HeaderColumnNameTranslateMappingStrategy<StockDetails> mappingStrategy = new HeaderColumnNameTranslateMappingStrategy<StockDetails>();
		mappingStrategy.setColumnMapping(columnMappings);
		mappingStrategy.setType(StockDetails.class);

		InputStream inputStream = file.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		CsvToBean<StockDetails> csvToBean = new CsvToBeanBuilder<StockDetails>(reader).withType(StockDetails.class)
				.withSeparator(',').withIgnoreLeadingWhiteSpace(true).withIgnoreEmptyLine(true)
				.withMappingStrategy(mappingStrategy).build();
		return csvToBean.parse();
	}

}