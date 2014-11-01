package ru.tsystems.tsproject.sbb;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.tsystems.tsproject.sbb.dao.DAOException;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.exception.PassengerNotExistsException;
import ru.tsystems.tsproject.sbb.service.api.PassengerService;

import java.util.HashSet;
import java.util.Set;

/**
 * Class, which create create UserDetails object for spring security. It gets data from passengerService
 * It passenger is not found or another error occurs, it return null, which means, that authentication failed
 * @author  Nikita Efremov
 * @since   2.0
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = Logger.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private PassengerService passengerService;

    @Override
    public UserDetails loadUserByUsername(String docNumber) throws UsernameNotFoundException {
        Passenger passenger;
        try {
            passenger = passengerService.findPassenger(docNumber);
        } catch (Exception e) {
            log.error("Error occurred while getting passenger " + docNumber + ". Error: " + e.getMessage());
            return null;
        }
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority("ROLE_PASSENGER"));

        return new User(passenger.getDocNumber(), passenger.getPassword(), roles);
    }
}
