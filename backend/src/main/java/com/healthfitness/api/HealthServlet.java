package com.healthfitness.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/api/health")
public final class HealthServlet extends HttpServlet {
    private final ObjectMapper json = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (Connection connection = Database.getConnection()) {
            if (!connection.isValid(2)) {
                response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                json.writeValue(response.getOutputStream(),
                        Map.of("status", "error", "database", "unavailable"));
                return;
            }
            json.writeValue(response.getOutputStream(),
                    Map.of("status", "ok", "database", "connected"));
        } catch (SQLException | IllegalStateException exception) {
            getServletContext().log("Health check could not connect to MySQL.", exception);
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            json.writeValue(response.getOutputStream(),
                    Map.of("status", "error", "database", "unavailable"));
        }
    }
}
