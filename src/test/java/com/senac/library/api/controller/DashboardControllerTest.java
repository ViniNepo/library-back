package com.senac.library.api.controller;

import com.senac.library.api.service.DashboardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.senac.library.api.mother.DashboardDtoMother.createdashboard;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = DashboardController.class)
@AutoConfigureMockMvc
class DashboardControllerTest {

    static String DASHBOARD_API = "/dashboard";

    @Autowired
    MockMvc mvc;

    @MockBean
    DashboardService service;

    @Test
    public void findAllSalesTest() throws Exception {

        given(service.getDashboard()).willReturn(createdashboard());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(DASHBOARD_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }
}