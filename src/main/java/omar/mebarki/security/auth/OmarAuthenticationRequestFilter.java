package omar.mebarki.security.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class OmarAuthenticationRequestFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (isNotAuthenticated()) {
            extractAuthenticationToken(httpServletRequest);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void extractAuthenticationToken(ServletRequest servletRequest) {
        String userName = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");
        if (userName != null && password != null) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

    private boolean isNotAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() == null ||
                !SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }
}
