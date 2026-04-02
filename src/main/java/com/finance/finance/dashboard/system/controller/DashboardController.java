package com.finance.finance.dashboard.system.controller;

import com.finance.finance.dashboard.system.dto.DashboardSummaryDTO;
import com.finance.finance.dashboard.system.model.FinancialRecord;
import com.finance.finance.dashboard.system.model.User;
import com.finance.finance.dashboard.system.service.DashboardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    // Summary
    @GetMapping("/summary")
    public DashboardSummaryDTO getSummary(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return service.getSummary(user);
    }

    // Category-wise
    @GetMapping("/category")
    public Map<String, Double> getCategorySummary(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return service.getCategorySummary(user);
    }

    // Recent Activity
    @GetMapping("/recent")
    public List<FinancialRecord> getRecent(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return service.getRecentActivity(user);
    }

    // Monthly Trend
    @GetMapping("/monthly")
    public Map<Integer, Double> getMonthly(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return service.getMonthlyTrend(user);
    }
}
