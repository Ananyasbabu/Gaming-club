package com.gaming.gaming.membership;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MembershipException extends RuntimeException {
    public MembershipException(String msg) {
        super(msg);
    }
}
