package com.healthfitness.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

@WebServlet(urlPatterns = {"/api/auth/register", "/api/auth/login", "/api/auth/me", "/api/auth/logout"})
public final class AuthServlet extends HttpServlet {
    private static final String USER_ID_ATTRIBUTE = "userId";
    private final ObjectMapper json = new ObjectMapper();
    private final AuthService auth = new AuthService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (!"/api/auth/me".equals(request.getServletPath())) {
            sendError(response, HttpServletResponse.SC_NOT_FOUND, "Endpoint not found.");
            return;
        }

        HttpSession session = request.getSession(false);
        if (session == null || !(session.getAttribute(USER_ID_ATTRIBUTE) instanceof Long userId)) {
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Sign in is required.");
            return;
        }

        try {
            Optional<Account> account = auth.findById(userId);
            if (account.isEmpty()) {
                session.invalidate();
                sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Account no longer exists.");
                return;
            }
            sendJson(response, HttpServletResponse.SC_OK, account.get());
        } catch (SQLException | IllegalStateException exception) {
            log("Unable to read the signed-in account.", exception);
            sendError(response, HttpServletResponse.SC_SERVICE_UNAVAILABLE, "The account service is unavailable.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();

        try {
            switch (path) {
                case "/api/auth/register" -> register(request, response);
                case "/api/auth/login" -> login(request, response);
                case "/api/auth/logout" -> logout(request, response);
                default -> sendError(response, HttpServletResponse.SC_NOT_FOUND, "Endpoint not found.");
            }
        } catch (AuthService.ValidationException exception) {
            sendError(response, HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
        } catch (UserRepository.EmailAlreadyExistsException exception) {
            sendError(response, HttpServletResponse.SC_CONFLICT, "An account with this email already exists.");
        } catch (SQLException | IllegalStateException exception) {
            log("Authentication request failed.", exception);
            sendError(response, HttpServletResponse.SC_SERVICE_UNAVAILABLE, "The account service is unavailable.");
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        AuthRequest body = readBody(request, response);
        if (body == null) {
            return;
        }
        Account account = auth.register(body);
        sendJson(response, HttpServletResponse.SC_CREATED, account);
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        AuthRequest body = readBody(request, response);
        if (body == null) {
            return;
        }
        Account account = auth.authenticate(body.email(), body.password());
        if (account == null) {
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid email or password.");
            return;
        }

        HttpSession existing = request.getSession(false);
        if (existing != null) {
            existing.invalidate();
        }
        request.getSession(true).setAttribute(USER_ID_ATTRIBUTE, account.id());
        sendJson(response, HttpServletResponse.SC_OK, account);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    private AuthRequest readBody(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String contentType = request.getContentType();
        if (contentType == null || !contentType.toLowerCase().startsWith("application/json")) {
            sendError(response, HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Send a JSON request body.");
            return null;
        }
        try {
            return json.readValue(request.getInputStream(), AuthRequest.class);
        } catch (JsonProcessingException exception) {
            sendError(response, HttpServletResponse.SC_BAD_REQUEST, "Request body must be valid JSON.");
            return null;
        }
    }

    private void sendJson(HttpServletResponse response, int status, Object body) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        json.writeValue(response.getOutputStream(), body);
    }

    private void sendError(HttpServletResponse response, int status, String message) throws IOException {
        sendJson(response, status, Map.of("error", message));
    }
}
