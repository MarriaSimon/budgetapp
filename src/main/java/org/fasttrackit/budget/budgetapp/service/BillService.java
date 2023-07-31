package org.fasttrackit.budget.budgetapp.service;

import org.fasttrackit.budget.budgetapp.exception.ResourceNotFoundException;
import org.fasttrackit.budget.budgetapp.model.Bill;
import org.fasttrackit.budget.budgetapp.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public Bill addBill(Bill bill) {
        return billRepository.save(bill);
    }

    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bill not found for this category: " + id));
    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Bill updateBill(Long id, Bill updatedBill) {
        Bill existingBill = billRepository.findById(id).orElse(null);
        if (existingBill != null) {
            existingBill.setName(updatedBill.getName());
            existingBill.setAmount(updatedBill.getAmount());
            existingBill.setDueDate(updatedBill.getDueDate());
            existingBill.setCategory(updatedBill.getCategory());
            return billRepository.save(existingBill);
        }
        return null;
    }

    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }
}
