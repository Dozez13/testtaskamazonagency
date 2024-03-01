package com.example.demo.domain.database;

import com.example.demo.domain.document.SellerRetailReport;
import com.example.demo.domain.repository.SellerRetailReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@RequiredArgsConstructor
public class DefaultDatabaseInjector implements ApplicationRunner {
    private final ObjectMapper objectMapper;
    private final SellerRetailReportRepository sellerRetailReportRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SellerRetailReport sellerRetailReport = objectMapper.readValue(new File("test_report.json"), SellerRetailReport.class);
        sellerRetailReportRepository.save(sellerRetailReport);
    }
}
