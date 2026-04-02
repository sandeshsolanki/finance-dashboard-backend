package com.finance.finance.dashboard.system.repository;

import com.finance.finance.dashboard.system.model.FinancialRecord;
import com.finance.finance.dashboard.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FinancialRepository extends JpaRepository<FinancialRecord, Long> {

    // ================== EXISTING CRUD FILTERS ==================

    List<FinancialRecord> findByUser(User user);

    List<FinancialRecord> findByUserAndCategory(User user, String category);

    List<FinancialRecord> findByUserAndType(User user, String type);

    List<FinancialRecord> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);


    // ================== DASHBOARD AGGREGATIONS ==================

    // Total Income
    @Query("SELECT COALESCE(SUM(f.amount), 0) FROM FinancialRecord f WHERE f.user = :user AND f.type = 'income'")
    Double getTotalIncome(User user);

    // ✅ Total Expense
    @Query("SELECT COALESCE(SUM(f.amount), 0) FROM FinancialRecord f WHERE f.user = :user AND f.type = 'expense'")
    Double getTotalExpense(User user);

    // Category-wise totals
    @Query("SELECT f.category, SUM(f.amount) FROM FinancialRecord f WHERE f.user = :user GROUP BY f.category")
    List<Object[]> getCategoryWiseTotals(User user);

    //Recent Activity (latest 5 records)
    List<FinancialRecord> findTop5ByUserOrderByDateDesc(User user);

    //Monthly Trend (month number + total amount)
    @Query("SELECT MONTH(f.date), SUM(f.amount) FROM FinancialRecord f WHERE f.user = :user GROUP BY MONTH(f.date) ORDER BY MONTH(f.date)")
    List<Object[]> getMonthlyTotals(User user);

    //Weekly Trend
    @Query("SELECT WEEK(f.date), SUM(f.amount) FROM FinancialRecord f WHERE f.user = :user GROUP BY WEEK(f.date) ORDER BY WEEK(f.date)")
    List<Object[]> getWeeklyTotals(User user);
}