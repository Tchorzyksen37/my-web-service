package pl.tchorzyksen.ui.model.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tchorzyksen.shared.dto.UserDto;
import pl.tchorzyksen.service.UserService;
import pl.tchorzyksen.ui.model.request.UserRequestModel;
import pl.tchorzyksen.ui.model.response.UserResponse;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired UserService userService;

  @GetMapping
  public UserResponse getUser() {
    return new UserResponse();
  }

  @PostMapping
  public UserResponse createUser(@RequestBody UserRequestModel userRequestModel) {

    UserResponse userResponse = new UserResponse();

    UserDto userDto = new UserDto();
    BeanUtils.copyProperties(userRequestModel, userDto);

    UserDto createdUser = userService.createUser(userDto);
    BeanUtils.copyProperties(createdUser, userResponse);

    return userResponse;
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
