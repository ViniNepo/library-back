package com.senac.library.api.service;

import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private CustomerRepository consumerRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<Customer> customer = consumerRepository.getCustomerByEmail(email);

		if (customer.isPresent()) {
			return customer.get();
		}

		throw new UsernameNotFoundException("Invalid username or password.");
	}

}
