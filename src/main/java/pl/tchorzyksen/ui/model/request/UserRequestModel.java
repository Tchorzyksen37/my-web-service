package pl.tchorzyksen.ui.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestModel {

  private String firstName;

  private String lastName;

  private String email;

  private String password;
}
