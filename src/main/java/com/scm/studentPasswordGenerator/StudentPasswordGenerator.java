package com.scm.studentPasswordGenerator;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class StudentPasswordGenerator {
    private final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private final String DIGITS = "0123456789";
    private final String SPECIAL = "@$#&*";
    private final String ALL_ALLOWED = UPPER + LOWER + DIGITS + SPECIAL;

    public String generatePassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        // Ensure at least one character from each group
        password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

        // Fill the rest randomly from all allowed characters
        for (int i = 4; i < 12; i++) {
            password.append(ALL_ALLOWED.charAt(random.nextInt(ALL_ALLOWED.length())));
        }
        return password.toString();
    }
}
