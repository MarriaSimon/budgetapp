package org.fasttrackit.budget.budgetapp.service.repository;

import org.fasttrackit.budget.budgetapp.model.Bill;
import org.fasttrackit.budget.budgetapp.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByMonthContainingIgnoreCaseAndCategoryContainingIgnoreCase(String month, String category);

    List<Bill> findByCategoryContainingIgnoreCase(String category);
    List<Bill> findByMonthContainingIgnoreCase(String month);

    List<Bill> findByMonth(String month);

    List<Bill> findByMonthAndYear(String month, int year);

    List<Bill> findByYear(int year);
}
