package com.stocks.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stocks_data")
public class StockDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "quarter")
	private String quarter;

	@Column(name = "stock")
	private String stock;

	@Column(name = "date")
	private String date;

	@Column(name = "open")
	private String open;

	@Column(name = "high")
	private String high;

	@Column(name = "low")
	private String low;

	@Column(name = "close")
	private String close;

	@Column(name = "volume")
	private String volume;

	@Column(name = "percent_change_price")
	private String percentChangePrice;

	@Column(name = "percent_change_volume_over_last_wk")
	private String percentChangeVolumeOverLastWk;

	@Column(name = "previous_weeks_volume")
	private String previousWeeksVolume;

	@Column(name = "next_weeks_open")
	private String nextWeeksOpen;

	@Column(name = "next_weeks_close")
	private String nextWeeksClose;

	@Column(name = "percent_change_next_weeks_price")
	private String percentChangeNextWeeksPrice;

	@Column(name = "days_to_next_dividend")
	private String daysToNextDividend;

	@Column(name = "percent_return_next_dividend")
	private String percentReturnNextDividend;

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getPercentChangePrice() {
		return percentChangePrice;
	}

	public void setPercentChangePrice(String percentChangePrice) {
		this.percentChangePrice = percentChangePrice;
	}

	public String getPercentChangeVolumeOverLastWk() {
		return percentChangeVolumeOverLastWk;
	}

	public void setPercentChangeVolumeOverLastWk(String percentChangeVolumeOverLastWk) {
		this.percentChangeVolumeOverLastWk = percentChangeVolumeOverLastWk;
	}

	public String getPreviousWeeksVolume() {
		return previousWeeksVolume;
	}

	public void setPreviousWeeksVolume(String previousWeeksVolume) {
		this.previousWeeksVolume = previousWeeksVolume;
	}

	public String getNextWeeksOpen() {
		return nextWeeksOpen;
	}

	public void setNextWeeksOpen(String nextWeeksOpen) {
		this.nextWeeksOpen = nextWeeksOpen;
	}

	public String getNextWeeksClose() {
		return nextWeeksClose;
	}

	public void setNextWeeksClose(String nextWeeksClose) {
		this.nextWeeksClose = nextWeeksClose;
	}

	public String getPercentChangeNextWeeksPrice() {
		return percentChangeNextWeeksPrice;
	}

	public void setPercentChangeNextWeeksPrice(String percentChangeNextWeeksPrice) {
		this.percentChangeNextWeeksPrice = percentChangeNextWeeksPrice;
	}

	public String getDaysToNextDividend() {
		return daysToNextDividend;
	}

	public void setDaysToNextDividend(String daysToNextDividend) {
		this.daysToNextDividend = daysToNextDividend;
	}

	public String getPercentReturnNextDividend() {
		return percentReturnNextDividend;
	}

	public void setPercentReturnNextDividend(String percentReturnNextDividend) {
		this.percentReturnNextDividend = percentReturnNextDividend;
	}

	@Override
	public String toString() {
		return "StockDetails {quarter=" + quarter + ", stock=" + stock + ", date=" + date + ", open=" + open + ", high="
				+ high + ", low=" + low + ", close=" + close + ", volume=" + volume + ", percentChangePrice="
				+ percentChangePrice + ", percentChangeVolumeOverLastWk=" + percentChangeVolumeOverLastWk
				+ ", previousWeeksVolume=" + previousWeeksVolume + ", nextWeeksOpen=" + nextWeeksOpen
				+ ", nextWeeksClose=" + nextWeeksClose + ", percentChangeNextWeeksPrice=" + percentChangeNextWeeksPrice
				+ ", daysToNextDividend=" + daysToNextDividend + ", percentReturnNextDividend="
				+ percentReturnNextDividend + "}";
	}

}
