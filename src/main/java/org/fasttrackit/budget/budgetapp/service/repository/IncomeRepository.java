package org.fasttrackit.budget.budgetapp.service.repository;


import org.fasttrackit.budget.budgetapp.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByMonth(String month);

    List<Income> findByYear(int year);
}
