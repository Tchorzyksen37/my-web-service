package pl.tchorzyksen.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.tchorzyksen.shared.dto.UserDto;

public interface UserService extends UserDetailsService {

  UserDto createUser(UserDto userDto);

  UserDto getUser(String email);
}
