package com.senac.library.api.config.security;

import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.repository.CustomerRepository;
import com.senac.library.api.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private CustomerRepository customerRepository;

    public AuthenticationTokenFilter(TokenService tokenService, CustomerRepository customerRepository) {
        this.tokenService = tokenService;
        this.customerRepository = customerRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = retrieveToken(request);
        boolean isValid = tokenService.isValidToken(token);

        if (isValid) {
            authenticateCustomer(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateCustomer(String token) {
        Long idCustomer = tokenService.getIdCustomer(token);
        Optional<Customer> optionalCustomer = customerRepository.findById(idCustomer);
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customer,
                    customer.getPassword(), customer.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private String retrieveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }

        return token.split(" ")[1];
    }
}
