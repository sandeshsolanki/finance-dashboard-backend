package com.finance.finance.dashboard.system.controller;


import com.finance.finance.dashboard.system.dto.FinancialDTO;
import com.finance.finance.dashboard.system.model.FinancialRecord;
import com.finance.finance.dashboard.system.model.User;
import com.finance.finance.dashboard.system.service.FinancialService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class FinancialController {

    private final FinancialService service;

    public FinancialController(FinancialService service) {
        this.service = service;
    }

    @PostMapping
    public FinancialRecord create(@RequestBody FinancialDTO dto,
                                  HttpServletRequest request) {

        User user = (User) request.getAttribute("user");
        return service.create(dto, user);
    }

    @GetMapping
    public List<FinancialRecord> getAll() {
        return service.getAll();
    }
}
