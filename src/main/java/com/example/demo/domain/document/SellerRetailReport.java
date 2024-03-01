package com.example.demo.domain.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "report")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerRetailReport {
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;
    private ReportSpecification reportSpecification;
    private List<SalesAndTrafficDateStatistic> salesAndTrafficByDate;
    private List<SalesAndTrafficAsinStatistic> salesAndTrafficByAsin;


    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class ReportSpecification {
        private String reportType;
        private ReportOption reportOptions;
        private LocalDate dataStartTime;
        private LocalDate dataEndTime;
        private List<String> marketplaceIds;

        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class ReportOption {
            private String dateGranularity;
            private String asinGranularity;
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class SalesAndTrafficDateStatistic {

        private LocalDate date;
        private SalesDateStatistic salesByDate;
        private TrafficDateStatistic trafficByDate;


        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class SalesDateStatistic {
            private AmountCurrencyCode orderedProductSales;
            private AmountCurrencyCode orderedProductSalesB2B;
            private Integer unitsOrdered;
            private Integer unitsOrderedB2B;
            private Integer totalOrderItems;
            private Integer totalOrderItemsB2B;
            private AmountCurrencyCode averageSalesPerOrderItem;
            private AmountCurrencyCode averageSalesPerOrderItemB2B;
            private Double averageUnitsPerOrderItem;
            private Double averageUnitsPerOrderItemB2B;
            private AmountCurrencyCode averageSellingPrice;
            private AmountCurrencyCode averageSellingPriceB2B;
            private Integer unitsRefunded;
            private Double refundRate;
            private Integer claimsGranted;
            private AmountCurrencyCode claimsAmount;
            private AmountCurrencyCode shippedProductSales;
            private Integer unitsShipped;
            private Integer ordersShipped;

        }

        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class TrafficDateStatistic {
            private Integer browserPageViews;
            private Integer browserPageViewsB2B;
            private Integer mobileAppPageViews;
            private Integer mobileAppPageViewsB2B;
            private Integer pageViews;
            private Integer pageViewsB2B;
            private Integer browserSessions;
            private Integer browserSessionsB2B;
            private Integer mobileAppSessions;
            private Integer mobileAppSessionsB2B;
            private Integer sessions;
            private Integer sessionsB2B;
            private Double buyBoxPercentage;
            private Double buyBoxPercentageB2B;
            private Double orderItemSessionPercentage;
            private Double orderItemSessionPercentageB2B;
            private Double unitSessionPercentage;
            private Double unitSessionPercentageB2B;
            private Integer averageOfferCount;
            private Integer averageParentItems;
            private Integer feedbackReceived;
            private Integer negativeFeedbackReceived;
            private Integer receivedNegativeFeedbackRate;


        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class SalesAndTrafficAsinStatistic {

        private String parentAsin;
        private SalesAsinStatistic salesByAsin;
        private TrafficAsinStatistic trafficByAsin;

        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class SalesAsinStatistic {
            private Integer unitsOrdered;
            private Integer unitsOrderedB2B;
            private AmountCurrencyCode orderedProductSales;
            private AmountCurrencyCode orderedProductSalesB2B;
            private Integer totalOrderItems;
            private Integer totalOrderItemsB2B;
        }

        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class TrafficAsinStatistic {
            private Integer browserSessions;
            private Integer browserSessionsB2B;
            private Integer mobileAppSessions;
            private Integer mobileAppSessionsB2B;
            private Integer sessions;
            private Integer sessionsB2B;
            private Double browserSessionPercentage;
            private Double browserSessionPercentageB2B;
            private Double mobileAppSessionPercentage;
            private Double mobileAppSessionPercentageB2B;
            private Double sessionPercentage;
            private Double sessionPercentageB2B;
            private Integer browserPageViews;
            private Integer browserPageViewsB2B;
            private Integer mobileAppPageViews;
            private Integer mobileAppPageViewsB2B;
            private Integer pageViews;
            private Integer pageViewsB2B;
            private Double browserPageViewsPercentage;
            private Double browserPageViewsPercentageB2B;
            private Double mobileAppPageViewsPercentage;
            private Double mobileAppPageViewsPercentageB2B;
            private Double pageViewsPercentage;
            private Double pageViewsPercentageB2B;
            private Double buyBoxPercentage;
            private Double buyBoxPercentageB2B;
            private Double unitSessionPercentage;
            private Double unitSessionPercentageB2B;

        }

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class AmountCurrencyCode {
        private Double amount;
        private String currencyCode;
    }
}
