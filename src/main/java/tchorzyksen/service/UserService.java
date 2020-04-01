package tchorzyksen.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import tchorzyksen.shared.dto.UserDto;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto userDto);

}
