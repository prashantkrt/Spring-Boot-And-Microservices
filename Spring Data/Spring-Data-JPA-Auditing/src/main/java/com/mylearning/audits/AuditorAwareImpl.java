package com.mylearning.audits;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //fetch the current user who logged in
        //SecurityContextHolder
        //get the principal
        //get the user
        return Optional.of("Current_User"); // so by default createdBy and lastModifiedBy
    }
}
