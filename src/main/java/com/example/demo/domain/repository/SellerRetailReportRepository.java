package com.example.demo.domain.repository;

import com.example.demo.domain.document.SellerRetailReport;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SellerRetailReportRepository extends MongoRepository<SellerRetailReport, String> {
    @Aggregation(pipeline = {"{'$unwind':'$salesAndTrafficByDate'}",
            "{'$match':{'salesAndTrafficByDate.date':{'$gt': :#{#startDate},'$lt': :#{#endDate}} }  }",
            "{'$replaceRoot':{'newRoot':'$salesAndTrafficByDate'}}"})
    List<SellerRetailReport.SalesAndTrafficDateStatistic> findSalesAndTrafficDateStatisticBetweenStartDateAndEndDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Aggregation(pipeline = {"{'$unwind':'$salesAndTrafficByDate'}",
            "{'$match':{'salesAndTrafficByDate.date':{'$eq': :#{#date}} }  }",
            "{'$replaceRoot':{'newRoot':'$salesAndTrafficByDate'}}"})
    SellerRetailReport.SalesAndTrafficDateStatistic findSalesAndTrafficDateStatisticByDate(@Param("date") LocalDate date);

    @Aggregation(pipeline = {"{'$unwind':'$salesAndTrafficByDate'}",
            "{'$replaceRoot':{'newRoot':'$salesAndTrafficByDate'}}"})
    List<SellerRetailReport.SalesAndTrafficDateStatistic> findAllSalesAndTrafficDateStatistic();

    @Aggregation(pipeline = {"{'$unwind':'$salesAndTrafficByAsin'}",
            "{'$match':{'salesAndTrafficByAsin.parentAsin':{'$eq': :#{#parentAsin}} }  }",
            "{'$replaceRoot':{'newRoot':'$salesAndTrafficByAsin'}}"})
    SellerRetailReport.SalesAndTrafficAsinStatistic findSalesAndTrafficAsinStatisticByAsin(@Param("parentAsin") String parentAsin);

    @Aggregation(pipeline = {"{'$unwind':'$salesAndTrafficByAsin'}",
            "{'$match':{'salesAndTrafficByAsin.parentAsin':{'$in': :#{#parentAsins}} }  }",
            "{'$replaceRoot':{'newRoot':'$salesAndTrafficByAsin'}}"})
    List<SellerRetailReport.SalesAndTrafficAsinStatistic> findSalesAndTrafficAsinStatisticByMultipleAsin(@Param("parentAsins") List<String> parentAsins);

    @Aggregation(pipeline = {"{'$unwind':'$salesAndTrafficByAsin'}",
            "{'$replaceRoot':{'newRoot':'$salesAndTrafficByAsin'}}"})
    List<SellerRetailReport.SalesAndTrafficAsinStatistic> findAllSalesAndTrafficAsinStatistic();

    @Query(value = "{}",fields = "{ '_id' : 1 }")
    SellerRetailReport findFirstId();
}
