package org.lpro.geoquizzback.config;

import org.lpro.geoquizzback.Exception.NotFound;
import org.lpro.geoquizzback.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class AppAuthProvider extends DaoAuthenticationProvider {
    @Autowired
    UserService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws NotFound {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String name = auth.getName();
        String password = auth.getCredentials()
                .toString();
        User user = userDetailsService.loadUserByUsername(name);
        System.out.println(user.getPassword()+" "+password);
        if (verifyHash(password,user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        } else {
            throw new NotFound("Bad pass or username");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}


