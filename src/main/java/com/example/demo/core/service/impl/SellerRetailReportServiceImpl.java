package com.example.demo.core.service.impl;

import com.example.demo.core.service.SellerRetailReportService;
import com.example.demo.domain.document.SellerRetailReport;
import com.example.demo.domain.repository.SellerRetailReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerRetailReportServiceImpl implements SellerRetailReportService {
    private final SellerRetailReportRepository sellerRetailReportRepository;

    @Cacheable("saleAndTrafficDateStatistic")
    @Override
    public List<SellerRetailReport.SalesAndTrafficDateStatistic> findSalesAndTrafficDateStatisticBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate) {
        return sellerRetailReportRepository.findSalesAndTrafficDateStatisticBetweenStartDateAndEndDate(startDate, endDate);
    }

    @Cacheable("saleAndTrafficDateStatistic")
    @Override
    public SellerRetailReport.SalesAndTrafficDateStatistic findSalesAndTrafficDateStatisticByDate(LocalDate date) {
        return sellerRetailReportRepository.findSalesAndTrafficDateStatisticByDate(date);
    }

    @Cacheable("saleAndTrafficDateStatistic")
    @Override
    public List<SellerRetailReport.SalesAndTrafficDateStatistic> findAllSalesAndTrafficDateStatistic() {
        return sellerRetailReportRepository.findAllSalesAndTrafficDateStatistic();
    }

    @Cacheable("saleAndTrafficAsinStatistic")
    @Override
    public SellerRetailReport.SalesAndTrafficAsinStatistic findSalesAndTrafficAsinStatisticByAsin(String parentAsin) {
        return sellerRetailReportRepository.findSalesAndTrafficAsinStatisticByAsin(parentAsin);
    }

    @Cacheable("saleAndTrafficAsinStatistic")
    @Override
    public List<SellerRetailReport.SalesAndTrafficAsinStatistic> findSalesAndTrafficAsinStatisticByMultipleAsin(List<String> parentAsins) {
        return sellerRetailReportRepository.findSalesAndTrafficAsinStatisticByMultipleAsin(parentAsins);
    }

    @Cacheable("saleAndTrafficAsinStatistic")
    @Override
    public List<SellerRetailReport.SalesAndTrafficAsinStatistic> findAllSalesAndTrafficAsinStatistic() {
        return sellerRetailReportRepository.findAllSalesAndTrafficAsinStatistic();
    }
}
