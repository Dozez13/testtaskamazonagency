package com.example.demo.core.event;

import com.example.demo.domain.document.SellerRetailReport;
import com.example.demo.domain.repository.SellerRetailReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FileChangedListener {

    private final ObjectMapper objectMapper;
    private final SellerRetailReportRepository sellerRetailReportRepository;

    @EventListener(FileChangedEvent.class)
    public void processFileChangedEvent() throws IOException {
        SellerRetailReport dbSellerRetailReport = sellerRetailReportRepository.findFirstId();
        SellerRetailReport fileSellerRetailReport = objectMapper.readValue(new File("test_report.json"), SellerRetailReport.class);
        dbSellerRetailReport.setReportSpecification(fileSellerRetailReport.getReportSpecification());
        dbSellerRetailReport.setSalesAndTrafficByDate(fileSellerRetailReport.getSalesAndTrafficByDate());
        dbSellerRetailReport.setSalesAndTrafficByAsin(fileSellerRetailReport.getSalesAndTrafficByAsin());
        sellerRetailReportRepository.save(dbSellerRetailReport);
    }
}
