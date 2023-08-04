package org.fasttrackit.budget.budgetapp.service;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.budget.budgetapp.model.Bill;
import org.fasttrackit.budget.budgetapp.model.MonthlyReport;
import org.fasttrackit.budget.budgetapp.model.Expense;
import org.fasttrackit.budget.budgetapp.model.YearlyReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private BillService billService;
    private ExpenseService expenseService;
    private IncomeService incomeService;

    @Autowired
    public ReportService(BillService billService, ExpenseService expenseService, IncomeService incomeService) {
        this.billService = billService;
        this.expenseService = expenseService;
        this.incomeService = incomeService;
    }

    public MonthlyReport generateMonthlyReport(String month) {
        MonthlyReport report = new MonthlyReport();

        // Get the current year if no month is provided
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        // If a specific month is provided, use it for filtering
        if (month != null) {
            List<Bill> billsForMonth = billService.getBillsByMonthAndCategory(month, null);
            List<Expense> expensesForMonth = expenseService.getExpensesByMonthAndCategory(month, null);

            double totalIncomeForMonth = incomeService.getSumOfIncomeForMonth(month);
            double totalBillsForMonth = billsForMonth.stream().mapToDouble(Bill::getAmount).sum();
            double totalExpensesForMonth = expensesForMonth.stream().mapToDouble(Expense::getAmount).sum();
            double remainingAmountForMonth = totalIncomeForMonth - totalBillsForMonth - totalExpensesForMonth;

            report.setMonth(month);
            report.setYear(currentYear);
            report.setIncome(totalIncomeForMonth);
            report.setTotalBills(totalBillsForMonth);
            report.setTotalExpenses(totalExpensesForMonth);
            report.setRemainingAmount(remainingAmountForMonth);
        } else {
            // Generate the report for the current month if no specific month is provided
            int currentMonth = currentDate.getMonthValue();

            List<Bill> billsForMonth = billService.getBillsByMonthAndCategory(String.format("%02d", currentMonth), null);
            List<Expense> expensesForMonth = expenseService.getExpensesByMonthAndCategory(String.format("%02d", currentMonth), null);

            double totalIncomeForMonth = incomeService.getSumOfIncomeForMonth(String.format("%02d", currentMonth));
            double totalBillsForMonth = billsForMonth.stream().mapToDouble(Bill::getAmount).sum();
            double totalExpensesForMonth = expensesForMonth.stream().mapToDouble(Expense::getAmount).sum();
            double remainingAmountForMonth = totalIncomeForMonth - totalBillsForMonth - totalExpensesForMonth;

            report.setMonth(String.format("%02d", currentMonth));
            report.setYear(currentYear);
            report.setIncome(totalIncomeForMonth);
            report.setTotalBills(totalBillsForMonth);
            report.setTotalExpenses(totalExpensesForMonth);
            report.setRemainingAmount(remainingAmountForMonth);
        }

        return report;
    }


    public YearlyReport generateYearlyReport() {
        YearlyReport report = new YearlyReport();

        // Get the current year
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        // Generate yearly report
        double yearlyIncome = incomeService.getSumOfIncomeForYear(currentYear);
        double yearlyBills = billService.getSumOfBillsForYear(currentYear);
        double yearlyExpenses = expenseService.getSumOfExpensesForYear(currentYear);
        double yearlyRemainingAmount = yearlyIncome - yearlyBills - yearlyExpenses;

        report.setYear(currentYear);
        report.setYearlyIncome(yearlyIncome);
        report.setYearlyBills(yearlyBills);
        report.setYearlyExpenses(yearlyExpenses);
        report.setYearlyRemainingAmount(yearlyRemainingAmount);

        return report;
    }
}







