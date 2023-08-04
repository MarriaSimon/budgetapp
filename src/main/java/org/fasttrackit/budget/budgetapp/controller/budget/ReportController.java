package org.fasttrackit.budget.budgetapp.controller.budget;


import lombok.RequiredArgsConstructor;
import org.fasttrackit.budget.budgetapp.model.MonthlyReport;
import org.fasttrackit.budget.budgetapp.model.YearlyReport;
import org.fasttrackit.budget.budgetapp.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")  //http://post:port/reports
@RequiredArgsConstructor
public class ReportController {

    @Autowired
    private final ReportService reportService;


    @GetMapping("/monthly")
    public MonthlyReport getMonthlyReport(@RequestParam(required = false) String month) {
        return reportService.generateMonthlyReport(month);
    }

    @GetMapping("/yearly")
    public YearlyReport getYearlyReport() {
        return reportService.generateYearlyReport();
    }
}

