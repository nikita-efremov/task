package ru.tsystems.tsproject.sbb.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.service.api.PassengerService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class, which create UserDetails object for user for spring security.
 * It gets data from passengerService and hardcoded admin user details
 * @author  Nikita Efremov
 * @since   2.0
 */
@Component
@Qualifier("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = Logger.getLogger(UserDetailsServiceImpl.class);
    private static Map<String,String> adminUsers;

    static {
        adminUsers = new HashMap<String, String>();
        adminUsers.put("admin", "d8578edf8458ce06fbc5bb76a58c5ca4");
    }

    @Autowired
    private PassengerService passengerService;

    /**
     * Gets passenger by its docNumber, which is username and admin by username
     * @param docNumber
     *        Document number of passenger
     * @return Found user details object
     * @throws UsernameNotFoundException
     *         If user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String docNumber) throws UsernameNotFoundException {
        if (adminUsers.containsKey(docNumber)) {
            Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
            roles.add(new SimpleGrantedAuthority(UserRole.ROLE_ADMIN.toString()));
            return new User(docNumber, adminUsers.get(docNumber), roles);
        } else {
            log.error("Cannot find admin with username: " + docNumber);
            try {
                Passenger passenger = passengerService.findPassenger(docNumber);
                Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
                roles.add(new SimpleGrantedAuthority(UserRole.ROLE_PASSENGER.toString()));
                return new User(passenger.getDocNumber(), passenger.getPassword(), roles);
            } catch (Exception e) {
                log.error("Error occurred while getting passenger " + docNumber + ". Error: " + e.getMessage());
                throw new UsernameNotFoundException("Cannot find passenger with docNumber: " + docNumber);
            }
        }
    }
}
