package org.fasttrackit.budget.budgetapp.repository;

import org.fasttrackit.budget.budgetapp.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
