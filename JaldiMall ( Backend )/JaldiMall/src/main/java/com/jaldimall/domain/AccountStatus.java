package com.jaldimall.domain;

public enum AccountStatus {
    PENDING_VERIFICATION,   // Account is created but not yet verified
    ACTIVE,                 // account is active and in good standing
    SUSPENDED,              // account is temporarily suspended, possibly due to violations
    DEACTIVATED,            //account is deactivated, user may have chosen to deactivate
    BANNED,                 // account is permanently banned due to serve violations
    CLOSED                  //account is permanently closed, possibly at user request
}
