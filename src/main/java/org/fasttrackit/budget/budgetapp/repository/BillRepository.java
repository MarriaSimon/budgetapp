package org.fasttrackit.budget.budgetapp.repository;

import org.fasttrackit.budget.budgetapp.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByMonthContainingIgnoreCaseAndCategoryContainingIgnoreCase(String month, String category);

    List<Bill> findByCategoryContainingIgnoreCase(String category);
    List<Bill> findByMonthContainingIgnoreCase(String month);

    List<Bill> findByMonth(String month);
}
