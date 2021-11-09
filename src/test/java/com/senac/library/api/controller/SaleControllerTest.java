package com.senac.library.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senac.library.api.model.dto.SaleDto;
import com.senac.library.api.model.request.SaleRequest;
import com.senac.library.api.service.SaleService;
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

import java.util.List;
import java.util.stream.Collectors;

import static com.senac.library.api.mother.SaleMother.createListSale;
import static com.senac.library.api.mother.SaleMother.createSale;
import static com.senac.library.api.mother.SaleRequestMother.createSaleRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = SaleController.class)
@AutoConfigureMockMvc
class SaleControllerTest {

    static String SALE_API = "/sale";

    @Autowired
    MockMvc mvc;

    @MockBean
    SaleService service;

    @Test
    public void findAllSalesTest() throws Exception {

        List<SaleDto> saleDtoList = createListSale().stream().map(SaleDto::new).collect(Collectors.toList());
        given(service.getAllSales()).willReturn(saleDtoList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(SALE_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void findSaleByIdTest() throws Exception {

        given(service.getSaleById(anyLong())).willReturn(new SaleDto(createSale()));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(SALE_API.concat("/1"))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void findSaleByCustomerIdTest() throws Exception {

        List<SaleDto> saleDtoList = createListSale().stream().map(SaleDto::new).collect(Collectors.toList());
        given(service.getAllSales()).willReturn(saleDtoList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(SALE_API.concat("/customer/1"))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void createSaleTest() throws Exception {

        given(service.createSale(any(SaleRequest.class))).willReturn(new SaleDto(createSale()));
        String json = new ObjectMapper().writeValueAsString(createSaleRequest());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(SALE_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isNotEmpty());
    }
}