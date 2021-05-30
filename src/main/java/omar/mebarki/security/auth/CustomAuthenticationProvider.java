package omar.mebarki.security.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if ("omar".equals(authentication.getPrincipal()) && "pass".equals(authentication.getCredentials())){
            return new UsernamePasswordAuthenticationToken("ddd", "fff");
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
