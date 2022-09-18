package ua.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.app.models.AppUser;
import ua.app.repositories.UserRepository;
import ua.app.security.AppUserDetails;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<AppUser> user = userRepository.findByUserName(userName);
        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return new AppUserDetails(user.get());
    }
}
