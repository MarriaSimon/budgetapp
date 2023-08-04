package org.fasttrackit.budget.budgetapp.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.fasttrackit.budget.budgetapp.exception.ResourceNotFoundException;
import org.fasttrackit.budget.budgetapp.model.Bill;
import org.fasttrackit.budget.budgetapp.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Getter
public class BillService {

    @Autowired
    private BillRepository billRepository;
    private List<Bill> billList = new ArrayList<>();

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Bill addBill(Bill bill) {
        return billRepository.save(bill);
    }

    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bill not found for this category: " + id));
    }

    public Bill updateBill(Long id, Bill updatedBill) {
        Bill existingBill = billRepository.findById(id).orElse(null);
        if (existingBill != null) {
            existingBill.setName(updatedBill.getName());
            existingBill.setAmount(updatedBill.getAmount());
            existingBill.setDueDate(updatedBill.getDueDate());
            existingBill.setCategory(updatedBill.getCategory());
            existingBill.setMonth(updatedBill.getMonth());
            return billRepository.save(existingBill);
        }
        return null;
    }

    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }

    public List<Bill> getAllBillsFilterable(String name, String Category, double minAmount, double maxAmount, String dueDate) {
        return null;
    }

        //filter the bills by month or by category
        public List<Bill> getBillsByMonthAndCategory(String month, String category) {
            if (month != null && category != null) {
                return billRepository.findByMonthContainingIgnoreCaseAndCategoryContainingIgnoreCase(month, category);
            } else if (month != null) {
                return billRepository.findAll().stream()
                        .filter(bill -> bill.getMonth().equalsIgnoreCase(month))
                        .collect(Collectors.toList());
            } else if (category != null) {
                return billRepository.findByCategoryContainingIgnoreCase(category);
            } else {
                return billRepository.findAll();
            }
        }

//    private String extractMonthFromDueDate(String dueDate) {
//
//        String[] dateParts = dueDate.split("-");
//        if (dateParts.length >= 2) {
//            return dateParts[1];
//        }
//        return "";
//    }

    public double getSumOfBillsForMonth(String month) {
        List<Bill> allBills = billRepository.findByMonth(month);

        double sum = allBills.stream()
                .mapToDouble(Bill::getAmount)
                .sum();

        return sum;
    }
}
