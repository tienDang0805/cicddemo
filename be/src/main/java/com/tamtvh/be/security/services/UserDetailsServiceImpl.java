package com.tamtvh.be.security.services;


import com.tamtvh.be.dto.AccountDTO;
import com.tamtvh.be.dto.CustomerDTO;
import com.tamtvh.be.dto.StaffDTO;
import com.tamtvh.be.model.Customer;
import com.tamtvh.be.model.Staff;
import com.tamtvh.be.repository.CustomerRepository;
import com.tamtvh.be.repository.StaffRepository;
import com.tamtvh.be.service.CustomerService;
import com.tamtvh.be.service.StaffService;
import com.tamtvh.be.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    CustomerService customerService;

    @Autowired
    StaffService staffService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerService.findByUsername(username);
        Staff staff = staffService.findByUsername(username);
        if (Objects.isNull(customer) && Objects.isNull(staff)) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
//        AccountDTO user = accountRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        AccountDTO user = new AccountDTO();
        if (Objects.nonNull(customer)) {
            user.setUsername(customer.getUSERNAME());
            user.setPassword(customer.getPASSWORD());
            user.setRole(customer.getRole1());
        }
        if (Objects.nonNull(staff)) {
            user.setUsername(staff.getUSERNAME());
            user.setPassword(staff.getPASSWORD());
            user.setRole(staff.getRole2());
        }
        return UserDetailsImpl.build(user);
    }

}
