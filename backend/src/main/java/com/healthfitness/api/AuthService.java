package com.healthfitness.api;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;

public final class AuthService {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    private final UserRepository users;

    public AuthService() {
        this(new UserRepository());
    }

    AuthService(UserRepository users) {
        this.users = users;
    }

    public Account register(AuthRequest request) throws SQLException {
        if (request == null) {
            throw new ValidationException("Request body is required.");
        }

        String fullName = request.fullName() == null ? "" : request.fullName().strip();
        String email = request.email() == null ? "" : request.email().strip().toLowerCase(Locale.ROOT);
        String phone = request.phone() == null ? "" : request.phone().strip();
        String password = request.password();

        if (fullName.isEmpty() || fullName.length() > 120) {
            throw new ValidationException("Full name is required and must be at most 120 characters.");
        }
        if (email.length() > 254 || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException("Enter a valid email address.");
        }
        if (phone.length() < 7 || phone.length() > 30) {
            throw new ValidationException("Phone number is required and must be 7 to 30 characters.");
        }
        if (password == null || password.length() < 8 || password.length() > 128) {
            throw new ValidationException("Password must be 8 to 128 characters.");
        }

        return users.create(fullName, email, PasswordHasher.hash(password), phone);
    }

    public Account authenticate(String email, String password) throws SQLException {
        if (email == null || password == null) {
            return null;
        }

        Optional<UserRepository.StoredAccount> stored =
                users.findByEmail(email.strip().toLowerCase(Locale.ROOT));
        if (stored.isEmpty() || !PasswordHasher.matches(password, stored.get().passwordHash())) {
            return null;
        }
        return stored.get().account();
    }

    public Optional<Account> findById(long id) throws SQLException {
        return users.findById(id);
    }

    static final class ValidationException extends RuntimeException {
        ValidationException(String message) {
            super(message);
        }
    }
}
