package com.arcesi.cliniquemedical.security.validatorssecurity;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;

@Service
@Slf4j
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String email) {
     log.info("Inside methode test of EmailValidator email : {}",email);
        return true;
    }
}
