package com.senac.library.api.service.impl;

import com.senac.library.api.model.entities.Authority;
import com.senac.library.api.repository.AuthorityRepository;
import com.senac.library.api.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    private static String roleCustomer = "ROLE_CUSTOMER";

    @Override
    public Authority getRoleUser() {
        Optional<Authority> authority = authorityRepository.findByRole(roleCustomer);

        if (authority.isEmpty()) {
            return authorityRepository.save(new Authority(roleCustomer));
        } else {
            return authority.get();
        }
    }
}
