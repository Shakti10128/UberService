package org.shakti.uberauthservice.AuthFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.shakti.uberauthservice.Services.JwtServiceImpl;
import org.shakti.uberauthservice.Services.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtServiceImpl jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    // Constructor injection of required services
    public JwtAuthFilter(JwtServiceImpl jwtService, UserDetailsServiceImpl userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // ✅ 1. Extract the URI of the incoming request
        String path = request.getRequestURI();

        // ✅ 2. Skip authentication filter for signin and signup endpoints
        if (path.startsWith("/api/v1/auth/signin") || path.startsWith("/api/v1/auth/signup")) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ 3. Try to extract the JWT token from the request cookies
        String token = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("JwtToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        // ✅ 4. If no token is present, return a 401 Unauthorized response
        if (token == null) {
            sendErrorResponse(response, request, "Unauthorized: Missing Token");
            return;
        }

        // ✅ 5. Extract email (or username) from the token
        String email = jwtService.extractEmail(token);

        // ✅ 6. If email is invalid or authentication is already set → return error
        if (email == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            sendErrorResponse(response, request, "Unauthorized: Invalid Token");
            return;
        }

        // ✅ 7. Load UserDetails by email
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // ✅ 8. Validate token and, if valid, set authentication in the SecurityContext
        if (jwtService.validateToken(token, userDetails.getUsername())) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            // ✅ 9. Token invalid → return 401 Unauthorized
            sendErrorResponse(response, request, "Unauthorized: Invalid Token");
            return;
        }

        // ✅ 10. Continue with the filter chain for the next filters/middleware
        filterChain.doFilter(request, response);
    }

    /**
     * ✅ Builds and sends a standardized JSON 401 Unauthorized response
     *
     * @param response HttpServletResponse object to write the error response to
     * @param request HttpServletRequest object (to extract URI)
     * @param message The error message to send to the client
     */
    private void sendErrorResponse(HttpServletResponse response, HttpServletRequest request, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        // ✅ Build JSON response body
        String jsonResponse = String.format(
                "{\"success\":false,\"uri\":\"%s\",\"timestamp\":\"%s\",\"message\":\"%s\"}",
                request.getRequestURI(),
                java.time.ZonedDateTime.now().toString(),
                message
        );

        response.getWriter().write(jsonResponse);
    }
}


