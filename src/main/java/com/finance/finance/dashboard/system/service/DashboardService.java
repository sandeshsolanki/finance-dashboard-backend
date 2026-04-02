package com.finance.finance.dashboard.system.service;

import com.finance.finance.dashboard.system.dto.DashboardSummaryDTO;
import com.finance.finance.dashboard.system.model.FinancialRecord;
import com.finance.finance.dashboard.system.model.User;
import com.finance.finance.dashboard.system.repository.FinancialRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final FinancialRepository repo;

    public DashboardService(FinancialRepository repo) {
        this.repo = repo;
    }

    // SUMMARY (income, expense, balance)
    public DashboardSummaryDTO getSummary(User user) {

        Double income = repo.getTotalIncome(user);
        Double expense = repo.getTotalExpense(user);

        Double balance = income - expense;

        return new DashboardSummaryDTO(income, expense, balance);
    }

    // CATEGORY-WISE TOTALS
    public Map<String, Double> getCategorySummary(User user) {

        List<Object[]> results = repo.getCategoryWiseTotals(user);
        Map<String, Double> map = new HashMap<>();

        for (Object[] row : results) {
            String category = (String) row[0];
            Double amount = (Double) row[1];
            map.put(category, amount);
        }

        return map;
    }

    //  RECENT ACTIVITY
    public List<FinancialRecord> getRecentActivity(User user) {
        return repo.findTop5ByUserOrderByDateDesc(user);
    }

    // MONTHLY TREND
    public Map<Integer, Double> getMonthlyTrend(User user) {

        List<Object[]> results = repo.getMonthlyTotals(user);
        Map<Integer, Double> map = new HashMap<>();

        for (Object[] row : results) {
            Integer month = (Integer) row[0];
            Double amount = (Double) row[1];
            map.put(month, amount);
        }

        return map;
    }
}
