package store.ggun.user.config;

import store.ggun.user.domain.UserModel;
import store.ggun.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // 시큐리티 session(내부 Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = userRepository.findById(Long.valueOf(username));
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        log.info("User found: {}", user.get().getId());
        return new org.springframework.security.core.userdetails.User(
                String.valueOf(user.get().getId()),
//                user.get().getUsername(),
                user.get().getPassword(),
                user.get().getAuthorities()
        );
    }
}