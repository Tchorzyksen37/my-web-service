package pl.tchorzyksen.ui.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserRequestModel {

  private PersonRequestModel person;

  private String email;

  private String password;
}
