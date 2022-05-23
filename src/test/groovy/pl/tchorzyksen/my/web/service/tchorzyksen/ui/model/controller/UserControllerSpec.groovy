package pl.tchorzyksen.my.web.service.tchorzyksen.ui.model.controller


import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pl.tchorzyksen.my.web.service.service.UserService
import pl.tchorzyksen.my.web.service.shared.dto.PersonDto
import pl.tchorzyksen.my.web.service.shared.dto.UserDto
import pl.tchorzyksen.my.web.service.controllers.UserController
import PersonRequestModel
import UserRequestModel
import UserResponse
import spock.lang.Specification

class UserControllerSpec extends Specification {

  UserService userService = Mock(UserService.class)

  UserController userController = new UserController(userService: userService)

  def "Request is correctly mapped and passed to service"() {
    given: "User request model"
    UserRequestModel model = new UserRequestModel()
    model.setEmail("test@domain.com")
    model.setPerson(new PersonRequestModel(firstName: "firstName", lastName: "lastName"))
    model.setPassword("test")

    when:
    ResponseEntity<UserResponse> response = userController.createUser(model)

    then:
    1 * userService.createUser({
      verifyAll(it, UserDto)
          {
            assert it.email == model.email
            assert it.person.firstName == model.person.firstName
            assert it.person.lastName == model.person.lastName
            assert it.password == model.password
          }
    }) >> new UserDto(email: "test@domain.com", person: new PersonDto(firstName: "firstName", lastName: "lastName"))

    response.statusCode == HttpStatus.CREATED
    response.body.email == model.email
    response.body.person.firstName == model.person.firstName
    response.body.person.lastName == model.person.lastName

  }

}
