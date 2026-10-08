package com.healthfitness.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public final class UserRepository {
    public Account create(String fullName, String email, String passwordHash, String phone) throws SQLException {
        String sql = """
                INSERT INTO users (full_name, email, password_hash, phone)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, fullName);
            statement.setString(2, email);
            statement.setString(3, passwordHash);
            statement.setString(4, phone);
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (!generatedKeys.next()) {
                    throw new SQLException("MySQL did not return an ID for the newly created account.");
                }
                return new Account(generatedKeys.getLong(1), fullName, email, phone, "member");
            }
        } catch (SQLException exception) {
            if (exception.getErrorCode() == 1062) {
                throw new EmailAlreadyExistsException(exception);
            }
            throw exception;
        }
    }

    public Optional<StoredAccount> findByEmail(String email) throws SQLException {
        String sql = """
                SELECT id, full_name, email, phone, role, password_hash
                FROM users
                WHERE email = ?
                """;

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    return Optional.empty();
                }
                Account account = readAccount(result);
                return Optional.of(new StoredAccount(account, result.getString("password_hash")));
            }
        }
    }

    public Optional<Account> findById(long id) throws SQLException {
        String sql = "SELECT id, full_name, email, phone, role FROM users WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? Optional.of(readAccount(result)) : Optional.empty();
            }
        }
    }

    private Account readAccount(ResultSet result) throws SQLException {
        return new Account(
                result.getLong("id"),
                result.getString("full_name"),
                result.getString("email"),
                result.getString("phone"),
                result.getString("role"));
    }

    record StoredAccount(Account account, String passwordHash) {
    }

    static final class EmailAlreadyExistsException extends RuntimeException {
        EmailAlreadyExistsException(SQLException cause) {
            super(cause);
        }
    }
}
