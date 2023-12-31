package org.fasttrackit.budget.budgetapp.controller.budget;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.budget.budgetapp.model.Bill;
import org.fasttrackit.budget.budgetapp.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("bills") //http://post:port/bills
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping
    public Bill addBill(@RequestBody Bill bill) {
        return billService.addBill(bill);
    }

    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id) {
        return billService.getBillById(id);
    }

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    @PutMapping("/{id}")
    public Bill updateBill(@PathVariable Long id, @RequestBody Bill updatedBill) {
        updatedBill.setId(id);
        return billService.updateBill(id, updatedBill);
    }

    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
    }

    @GetMapping("/filter")
    public List<Bill> getFilteredBills(@RequestParam(required = false) String month, @RequestParam(required = false) String category) {
        return billService.getBillsByMonthAndCategory(month, category);
    }

    @GetMapping("/sum-for-month/{month}")
    public double getSumOfBillsForMonth(@PathVariable String month) {
        return billService.getSumOfBillsForMonth(month);
    }
}
