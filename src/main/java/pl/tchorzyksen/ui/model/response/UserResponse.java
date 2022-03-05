package pl.tchorzyksen.ui.model.response;

import lombok.Getter;
import lombok.Setter;
import pl.tchorzyksen.shared.dto.PersonDto;

@Getter
@Setter
public class UserResponse {

  private String userId;

  private PersonResponse person;

  private String email;
}
