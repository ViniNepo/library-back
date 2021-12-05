package com.senac.library.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senac.library.api.model.dto.LoginDto;
import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.service.CustomerService;
import com.senac.library.api.service.LoginService;
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

import static com.senac.library.api.mother.CustomerMother.createCustomer;
import static com.senac.library.api.mother.CustomerMother.createOptionCustomer;
import static com.senac.library.api.mother.LoginDtoMother.createLoginDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerController.class)
@ActiveProfiles("dev")
@AutoConfigureMockMvc
class CustomerControllerTest {

    static String CUSTOMER_API = "/customer";

    @Autowired
    MockMvc mvc;

    @MockBean
    LoginService loginService;

    @MockBean
    CustomerService service;

    @Test
    public void getCustomerByIdTest() throws Exception {

        given(service.getById(anyLong())).willReturn(createOptionCustomer());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(CUSTOMER_API.concat("/1"))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void getCustomerByEmailTest() throws Exception {

        String json = new ObjectMapper().writeValueAsString(createLoginDto());

        given(service.getByEmail(any(LoginDto.class))).willReturn(createOptionCustomer());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(CUSTOMER_API.concat("/1"))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .param(json);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void createCustomerTest() throws Exception {

        Customer customer = createCustomer();
        given(service.createUser((any(Customer.class)))).willReturn(customer);

        customer.setId(null);
        String json = new ObjectMapper().writeValueAsString(customer);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(CUSTOMER_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isNotEmpty());
    }

    @Test
    public void updateCustomerTest() throws Exception {

        Customer customer = createCustomer();
        given(service.updateCustomer((any(Customer.class)))).willReturn(customer);
        String json = new ObjectMapper().writeValueAsString(customer);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(CUSTOMER_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isNotEmpty());
    }

    @Test
    public void deleteCustomerTest() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(CUSTOMER_API.concat("/1"))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

}