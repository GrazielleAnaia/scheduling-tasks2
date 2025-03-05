package com.grazielleanaia.scheduling_tasks2.infrastructure.security;


import com.grazielleanaia.scheduling_tasks2.business.dto.CustomerDTO;
import com.grazielleanaia.scheduling_tasks2.infrastructure.client.CustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private CustomerClient customerClient;

    public UserDetails loadUserByUsername(String email, String token) {
        CustomerDTO customerDTO = customerClient.findCustomerByEmail(email, token);
        return User
                .withUsername(customerDTO.getEmail())
                .password(customerDTO.getPassword())
                .build();
    }
}
