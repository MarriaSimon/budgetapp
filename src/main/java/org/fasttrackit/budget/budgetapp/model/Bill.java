package org.fasttrackit.budget.budgetapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Bill {
    public Bill() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "bill_month")
    private String month;
    @Column (name = "bill_name")
    private String name;
    @Column (name = "bill_amount")
    private double amount;

    @Column
    private String dueDate;

    @Column (name = "bill_category")
    private String category; //Bank Loan, Utilities, Grocery

    @ManyToOne
    private Income income;

}
