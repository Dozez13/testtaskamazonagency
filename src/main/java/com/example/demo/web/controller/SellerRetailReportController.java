package com.example.demo.web.controller;


import com.example.demo.core.service.SellerRetailReportService;
import com.example.demo.domain.document.SellerRetailReport;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/secure/seller-retail-reports")
@RequiredArgsConstructor
public class SellerRetailReportController {
    private final SellerRetailReportService sellerRetailReportService;

    @GetMapping(value = "/sales-traffic-date-statistic/date")
    @PreAuthorize("authentication.principal.role == 'USER'")
    public SellerRetailReport.SalesAndTrafficDateStatistic getSalesAndTrafficDateStatisticByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return sellerRetailReportService.findSalesAndTrafficDateStatisticByDate(date);
    }

    @GetMapping(value = "/sales-traffic-date-statistic/between-dates")
    @PreAuthorize("authentication.principal.role == 'USER'")
    public List<SellerRetailReport.SalesAndTrafficDateStatistic> getSalesAndTrafficDateStatisticBetweenDates(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return sellerRetailReportService.findSalesAndTrafficDateStatisticBetweenStartDateAndEndDate(startDate, endDate);
    }

    @GetMapping(value = "/sales-traffic-date-statistic")
    @PreAuthorize("authentication.principal.role == 'USER'")
    public List<SellerRetailReport.SalesAndTrafficDateStatistic> getSalesAndTrafficDateStatistic() {
        return sellerRetailReportService.findAllSalesAndTrafficDateStatistic();
    }

    @GetMapping(value = "/sales-traffic-asin-statistic/asin")
    @PreAuthorize("authentication.principal.role == 'USER'")
    public SellerRetailReport.SalesAndTrafficAsinStatistic getSalesAndTrafficAsinStatisticByAsin(@RequestParam("parentAsin") String parentAsin) {
        return sellerRetailReportService.findSalesAndTrafficAsinStatisticByAsin(parentAsin);
    }

    @GetMapping(value = "/sales-traffic-asin-statistic/multiple-asins")
    @PreAuthorize("authentication.principal.role == 'USER'")
    public List<SellerRetailReport.SalesAndTrafficAsinStatistic> getSalesAndTrafficAsinStatisticByMultipleAsins(@RequestParam("parentAsins") List<String> parentAsins) {
        return sellerRetailReportService.findSalesAndTrafficAsinStatisticByMultipleAsin(parentAsins);
    }

    @GetMapping(value = "/sales-traffic-asin-statistic")
    @PreAuthorize("authentication.principal.role == 'USER'")
    public List<SellerRetailReport.SalesAndTrafficAsinStatistic> getSalesAndTrafficAsinStatistic() {
        return sellerRetailReportService.findAllSalesAndTrafficAsinStatistic();
    }

}
