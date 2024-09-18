//package com.kokobato.huynhduc.kokobatodemo.filters;
//
//import com.kokobato.huynhduc.kokobatodemo.models.User;
//import com.kokobato.huynhduc.kokobatodemo.component.JwtTokenUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.util.Pair;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class JwtTokenFilter extends OncePerRequestFilter {
//    private final UserDetailsService userDetailsService;
//
//    private final JwtTokenUtil jwtTokenUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        try {
//            if (isBypassToken(request)) {
//                filterChain.doFilter(request, response);
//                return;
//            }
//
//            final String authHeader = request.getHeader("Authorization");
//
//            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//            }
//
//            String token = authHeader.substring(7);
//            String username = jwtTokenUtil.extractUsername(token);
//
//            if (username != null
//                    && SecurityContextHolder.getContext().getAuthentication() == null
//            ) {
//                User user = (User) userDetailsService.loadUserByUsername(username);
//
//                if (jwtTokenUtil.validateToken(token, user)) {
//                    UsernamePasswordAuthenticationToken authenticationToken =
//                            new UsernamePasswordAuthenticationToken(
//                                    user,
//                                    null,
//                                    user.getAuthorities()
//                            );
//
//                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                }
//            }
//
//            filterChain.doFilter(request, response);
//        } catch (Exception e) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//        }
//    }
//
//    private boolean isBypassToken(@NonNull HttpServletRequest request) {
//        final List<Pair<String, String>> bypassTokens = Arrays.asList(
//                Pair.of("/api/user/register", "POST"),
//                Pair.of("/api/user/login", "POST"),
//                Pair.of("/api/office/list", "GET"),
//                Pair.of("/api/employee/list", "GET")
//        );
//
//        for (Pair<String, String> bypasstoken : bypassTokens) {
//            if (request.getServletPath().contains(bypasstoken.getFirst())
//                    && request.getMethod().equals(bypasstoken.getSecond())
//            ) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//}
