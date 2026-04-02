package com.finance.finance.dashboard.system.service;


import com.finance.finance.dashboard.system.dto.FinancialDTO;
import com.finance.finance.dashboard.system.model.FinancialRecord;
import com.finance.finance.dashboard.system.model.Role;
import com.finance.finance.dashboard.system.model.User;
import com.finance.finance.dashboard.system.repository.FinancialRepository;
import com.finance.finance.dashboard.system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialService {

    private final FinancialRepository repo;
    private final UserRepository userRepo;

    public FinancialService(FinancialRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public FinancialRecord create(FinancialDTO dto, User currentUser) {

        if (currentUser.getRole() == Role.VIEWER) {
            throw new RuntimeException("Access Denied");
        }

        User user = userRepo.findById(dto.getUserId()).orElseThrow();

        FinancialRecord record = new FinancialRecord();
        record.setAmount(dto.getAmount());
        record.setType(dto.getType());
        record.setCategory(dto.getCategory());
        record.setDate(dto.getDate());
        record.setDescription(dto.getDescription());
        record.setUser(user);

        return repo.save(record);
    }

    public List<FinancialRecord> getAll() {
        return repo.findAll();
    }
}