package com.senac.library.api.controller;

import com.senac.library.api.model.dto.DashboardDto;
import com.senac.library.api.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.senac.library.api.excepitions.DashboardException.dashboardException;

@RestController
@RequestMapping(path = "dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardDto> getAllSales() {
        DashboardDto dashDto = dashboardService.getDashboard();

        if(dashDto == null) {
            dashboardException("dashboard cannot be null");
        }

        return ResponseEntity.ok(dashDto);
    }

}
