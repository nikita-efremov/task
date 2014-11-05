package ru.tsystems.tsproject.sbb.user;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class, which create create UserDetails object for admin for spring security
 * It admin is not found or another error occurs, it return null, which means, that authentication failed
 * @author  Nikita Efremov
 * @since   2.0
 */
@Component
public class AdminUserDetailsServiceImpl  implements UserDetailsService {

    private static final Logger log = Logger.getLogger(UserDetailsServiceImpl.class);

    /**
     * Gets admin by its username
     * @param username
     *        Username of admin
     * @return Found user details object
     * @throws UsernameNotFoundException
     *         If user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String,String> adminUsers = new HashMap<String, String>();
        adminUsers.put("admin", "d8578edf8458ce06fbc5bb76a58c5ca4");

        if (adminUsers.containsKey(username)) {
            Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
            roles.add(new SimpleGrantedAuthority(UserRole.ROLE_ADMIN.toString()));
            return new User(username, adminUsers.get(username), roles);
        } else {
            log.error("Cannot find admin with username: " + username);
            throw new UsernameNotFoundException("Cannot find admin with username: " + username);
        }
    }
}