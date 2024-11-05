package com.mylearning.audits;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //fetch the current user who logged in.. and use here
        //SecurityContextHolder
        //get the principal
        //get the user
        return Optional.of("Current_User");
    }
}
