package com.finance.finance.dashboard.system.service;

import com.finance.finance.dashboard.system.dto.FinancialDTO;
import com.finance.finance.dashboard.system.model.FinancialRecord;
import com.finance.finance.dashboard.system.model.Role;
import com.finance.finance.dashboard.system.model.User;
import com.finance.finance.dashboard.system.repository.FinancialRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FinancialService {

    private final FinancialRepository repo;

    public FinancialService(FinancialRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public FinancialRecord create(FinancialDTO dto, User currentUser) {

        if (currentUser.getRole() == Role.VIEWER) {
            throw new RuntimeException("Access Denied");
        }

        FinancialRecord record = new FinancialRecord();
        record.setAmount(dto.getAmount());
        record.setType(dto.getType());
        record.setCategory(dto.getCategory());
        record.setDate(dto.getDate());
        record.setDescription(dto.getDescription());
        record.setUser(currentUser);

        return repo.save(record);
    }

    // GET ALL (USER-SPECIFIC)
    public List<FinancialRecord> getAllByUser(User user) {
        return repo.findByUser(user);
    }

    // GET BY ID
    public FinancialRecord getById(Long id, User user) {
        FinancialRecord record = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        if (!record.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized access");
        }

        return record;
    }

    // UPDATE
    public FinancialRecord update(Long id, FinancialDTO dto, User user) {

        FinancialRecord record = getById(id, user);

        if (user.getRole() == Role.VIEWER) {
            throw new RuntimeException("Access Denied");
        }

        record.setAmount(dto.getAmount());
        record.setType(dto.getType());
        record.setCategory(dto.getCategory());
        record.setDate(dto.getDate());
        record.setDescription(dto.getDescription());

        return repo.save(record);
    }

    // DELETE
    public void delete(Long id, User user) {

        FinancialRecord record = getById(id, user);

        if (user.getRole() == Role.VIEWER) {
            throw new RuntimeException("Access Denied");
        }

        repo.delete(record);
    }

    // FILTER BY CATEGORY
    public List<FinancialRecord> getByCategory(String category, User user) {
        return repo.findByUserAndCategory(user, category);
    }

    // FILTER BY TYPE
    public List<FinancialRecord> getByType(String type, User user) {
        return repo.findByUserAndType(user, type);
    }

    // FILTER BY DATE RANGE
    public List<FinancialRecord> getByDateRange(String start, String end, User user) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return repo.findByUserAndDateBetween(user, startDate, endDate);
    }
}