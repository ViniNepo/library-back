package com.senac.library.api.model.dto;

import lombok.Data;

@Data
public class DashboardDto {

    private Integer totalClients = 0;
    private Integer totalBooksSoldToday = 0;
    private Integer totalBooks = 0;
    private BookDto bestSellerBook;
    private BookDto bookWithLowStorage;
    private BookDto bookWithMaxStorage;
    private Double wallet = 0.0;

    public DashboardDto(){
    }
}
