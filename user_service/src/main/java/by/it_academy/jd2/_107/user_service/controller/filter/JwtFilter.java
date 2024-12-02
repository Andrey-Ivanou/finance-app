package by.it_academy.jd2._107.user_service.controller.filter;

import by.it_academy.jd2._107.user_service.controller.utils.JwtTokenHandler;
import by.it_academy.jd2._107.user_service.models.UserPrincipal;
import by.it_academy.jd2._107.user_service.service.api.ICabinetService;
import by.it_academy.jd2._107.user_service.service.api.IUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final IUserService userService;
    private final ICabinetService cabinetService;
    private final JwtTokenHandler jwtHandler;
    private final UserPrincipal userPrincipal;

    public JwtFilter(IUserService userService, ICabinetService cabinetService, JwtTokenHandler jwtHandler, UserPrincipal userPrincipal) {
        this.userService = userService;
        this.cabinetService = cabinetService;
        this.jwtHandler = jwtHandler;
        this.userPrincipal = userPrincipal;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();
        if (!jwtHandler.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        UserPrincipal userPrincipal = cabinetService
                .getByLogin(jwtHandler.getUsername(token));

        if (userPrincipal == null) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userPrincipal, null,
                userPrincipal == null ?
                        List.of() : userPrincipal.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
