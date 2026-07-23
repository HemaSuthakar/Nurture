package com.cognizant.ormlearn.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.ormlearn.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    // Get all stock details of a given code between two dates
    // (e.g. Facebook stock in September 2019)
    List<Stock> findByCodeAndDateBetween(String code, LocalDate startDate, LocalDate endDate);

    // Get all stock details of a given code where close price is greater than a value
    // (e.g. Google stock where close > 1250)
    List<Stock> findByCodeAndCloseGreaterThan(String code, BigDecimal close);

    // Top 3 dates with the highest volume of transactions (across all stocks)
    List<Stock> findTop3ByOrderByVolumeDesc();

    // Top 3 dates with the lowest close price for a given stock code
    // (e.g. 3 dates when Netflix stocks were the lowest)
    List<Stock> findTop3ByCodeOrderByCloseAsc(String code);
}
