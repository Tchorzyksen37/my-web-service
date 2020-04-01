package tchorzyksen.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tchorzyksen.repositories.UserRepository;
import tchorzyksen.entity.UserEntity;
import tchorzyksen.service.UserService;
import tchorzyksen.shared.Utils;
import tchorzyksen.shared.dto.UserDto;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {

        if ( userRepository.findUserByEmail(userDto.getEmail()) != null )
            throw new RuntimeException("Record already exists");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);

        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);


        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        UserEntity storedUser = userRepository.save(userEntity);

        UserDto createdUser = new UserDto();
        BeanUtils.copyProperties(storedUser, createdUser);

        return createdUser;
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findUserByEmail(email);

        if (userEntity == null)
            throw new UsernameNotFoundException(email);

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findUserByEmail(email);

        if (userEntity == null)
            throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
