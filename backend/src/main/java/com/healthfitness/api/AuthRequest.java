package com.healthfitness.api;

public record AuthRequest(String fullName, String email, String password, String phone) {
}
