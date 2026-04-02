package com.finance.finance.dashboard.system.repository;

import com.finance.finance.dashboard.system.model.FinancialRecord;
import com.finance.finance.dashboard.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FinancialRepository extends JpaRepository<FinancialRecord, Long> {

    List<FinancialRecord> findByUser(User user);

    List<FinancialRecord> findByUserAndCategory(User user, String category);

    List<FinancialRecord> findByUserAndType(User user, String type);

    List<FinancialRecord> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);
}