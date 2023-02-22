package com.stocks.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stocks.demo.model.StockDetails;

public interface StocksRepository extends JpaRepository<StockDetails, Long> {
	/**
	 * Method to find stock list by name
	 * 
	 * @param name
	 * @return
	 */
	List<StockDetails> findByStock(String name);

}
