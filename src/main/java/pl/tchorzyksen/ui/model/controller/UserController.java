package pl.tchorzyksen.ui.model.controller;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tchorzyksen.service.UserService;
import pl.tchorzyksen.shared.dto.UserDto;
import pl.tchorzyksen.ui.model.request.UserRequestModel;
import pl.tchorzyksen.ui.model.response.UserResponse;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired private UserService userService;

  private final ModelMapper modelMapper = new ModelMapper();

  @GetMapping
  public UserResponse getUser() {
    return new UserResponse();
  }

  @PostMapping
  public ResponseEntity<UserResponse> createUser(@RequestBody UserRequestModel userRequestModel) {

    log.info("UserRequestModel {}", userRequestModel);

    UserDto userDto = modelMapper.map(userRequestModel, UserDto.class);

    log.debug("UserDto {}", userDto);

    return new ResponseEntity<>(
        modelMapper.map(userService.createUser(userDto), UserResponse.class), HttpStatus.CREATED);
  }

  @PutMapping
  public String updateUser() {
    return "Update user method invoked.";
  }

  @DeleteMapping
  public String deleteUser() {
    return "Delete user method invoked.";
  }
}
