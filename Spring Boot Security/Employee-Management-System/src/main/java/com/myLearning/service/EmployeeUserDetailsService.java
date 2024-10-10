package com.myLearning.service;

import com.myLearning.entity.Employee;
import com.myLearning.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findByUserName(username);
        return employee.map(EmployeeUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found in system"));
    }
}

/*
*   Optional allows us to use
*   map()
*   filter()
*   flatmap()
*   ifPresent()
*   ifPresentOrElse()
*   orElse()
*   orElseGet()
*   orElseThrow()
*   stream() etc..
*
*
*   Optional<List<Integer>> optionalList = Optional.of(Arrays.asList(1, 2, 3, 4, 5));*
*   Using map to transform the list
*   Optional<List<Integer>> result = optionalList.map(list -> list.stream()
*                                                             .map(x -> x * 2) // Multiply each element by 2
*                                                             .toList());*
*   System.out.println(result);  // Output: Optional[[2, 4, 6, 8, 10]]
*
* */
