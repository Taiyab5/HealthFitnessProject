package com.healthfitness.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PasswordHasherTest {
    @Test
    void hashesCanBeVerifiedWithoutStoringThePlaintext() {
        String password = "correct horse battery staple";
        String firstHash = PasswordHasher.hash(password);
        String secondHash = PasswordHasher.hash(password);

        assertNotEquals(password, firstHash);
        assertNotEquals(firstHash, secondHash);
        assertTrue(PasswordHasher.matches(password, firstHash));
        assertFalse(PasswordHasher.matches("wrong password", firstHash));
    }
}
