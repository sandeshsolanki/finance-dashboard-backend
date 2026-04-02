package com.finance.finance.dashboard.system.dto;



import lombok.Data;

import java.time.LocalDate;

@Data
public class FinancialDTO {
    private Double amount;
    private String type;
    private String category;
    private LocalDate date;
    private String description;
    private Long userId;
}
