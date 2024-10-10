package com.school.utils;

public interface IPasswordEncoder {
    public String encode(CharSequence rawPassword);

    public boolean matches(CharSequence rawPassword, String encodedPassword);
}
