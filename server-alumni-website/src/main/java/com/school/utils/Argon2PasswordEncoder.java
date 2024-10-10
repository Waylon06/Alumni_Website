package com.school.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Service;

@Service
public class Argon2PasswordEncoder implements IPasswordEncoder {
    private static final Argon2 ARGON2 = Argon2Factory.create();

    private static final int ITERATIONS = 2;
    private static final int MEMORY = 65536;
    private static final int PARALLELISM = 1;

    @Override
    public String encode(CharSequence rawPassword) {
        final String hash = ARGON2.hash(ITERATIONS, MEMORY, PARALLELISM, rawPassword.toString());
        return hash;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return ARGON2.verify(encodedPassword, rawPassword.toString());
    }
}
