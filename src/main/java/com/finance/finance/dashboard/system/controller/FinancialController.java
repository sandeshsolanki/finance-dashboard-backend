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

    // CREATE
    @PostMapping
    public FinancialRecord create(@RequestBody FinancialDTO dto,
                                  HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return service.create(dto, user);
    }

    // GET ALL
    @GetMapping
    public List<FinancialRecord> getAll(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return service.getAllByUser(user);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public FinancialRecord getById(@PathVariable Long id,
                                   HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return service.getById(id, user);
    }

    // UPDATE
    @PutMapping("/{id}")
    public FinancialRecord update(@PathVariable Long id,
                                  @RequestBody FinancialDTO dto,
                                  HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return service.update(id, dto, user);
    }

    //  DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id,
                         HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        service.delete(id, user);
        return "Record deleted successfully";
    }

    // FILTER BY CATEGORY
    @GetMapping("/category/{category}")
    public List<FinancialRecord> getByCategory(@PathVariable String category,
                                               HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return service.getByCategory(category, user);
    }

    // FILTER BY TYPE
    @GetMapping("/type/{type}")
    public List<FinancialRecord> getByType(@PathVariable String type,
                                           HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return service.getByType(type, user);
    }

    // FILTER BY DATE RANGE
    @GetMapping("/date")
    public List<FinancialRecord> getByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate,
            HttpServletRequest request) {

        User user = (User) request.getAttribute("user");
        return service.getByDateRange(startDate, endDate, user);
    }
}