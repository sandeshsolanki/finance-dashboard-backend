package com.finance.finance.dashboard.system.security;

import com.finance.finance.dashboard.system.model.User;
import com.finance.finance.dashboard.system.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository repo;

    public JwtFilter(JwtUtil jwtUtil, UserRepository repo) {
        this.jwtUtil = jwtUtil;
        this.repo = repo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain)
            throws ServletException, IOException {

        String path = req.getRequestURI();

        //  Skip public auth APIs
        if (path.startsWith("/auth")) {
            chain.doFilter(req, res);
            return;
        }

        String header = req.getHeader("Authorization");

        //  If no token → continue (Spring Security will handle)
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(req, res);
            return;
        }

        try {
            String token = header.substring(7);

            // Extract email from token
            String email = jwtUtil.extractEmail(token);

            // Fetch user from DB
            User user = repo.findByEmail(email).orElse(null);

            if (user != null) {

                // Set ROLE (VERY IMPORTANT)
                List<SimpleGrantedAuthority> authorities =
                        List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                authorities
                        );

                //  Set authentication in context
                SecurityContextHolder.getContext().setAuthentication(auth);

                // Attach user to request (for controllers)
                req.setAttribute("user", user);
            }

        } catch (Exception e) {
            // Invalid token → clear context
            SecurityContextHolder.clearContext();
        }

        chain.doFilter(req, res);
    }
}