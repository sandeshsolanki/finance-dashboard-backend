package com.finance.finance.dashboard.system.repository;




import com.finance.finance.dashboard.system.model.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialRepository extends JpaRepository<FinancialRecord, Long> {
}
