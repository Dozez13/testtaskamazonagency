package com.example.demo.core.service;

import com.example.demo.domain.document.SellerRetailReport;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SellerRetailReportService {

    List<SellerRetailReport.SalesAndTrafficDateStatistic> findSalesAndTrafficDateStatisticBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate);

    SellerRetailReport.SalesAndTrafficDateStatistic findSalesAndTrafficDateStatisticByDate(LocalDate date);

    List<SellerRetailReport.SalesAndTrafficDateStatistic> findAllSalesAndTrafficDateStatistic();

    SellerRetailReport.SalesAndTrafficAsinStatistic findSalesAndTrafficAsinStatisticByAsin(String parentAsin);

    List<SellerRetailReport.SalesAndTrafficAsinStatistic> findSalesAndTrafficAsinStatisticByMultipleAsin(List<String> parentAsins);

    List<SellerRetailReport.SalesAndTrafficAsinStatistic> findAllSalesAndTrafficAsinStatistic();
}
