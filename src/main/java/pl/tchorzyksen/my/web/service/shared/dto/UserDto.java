package pl.tchorzyksen.my.web.service.shared.dto;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class UserDto implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  private long id;

  private String userId;

  private PersonDto person;

  private String email;

  private String password;

  private String encryptedPassword;

  private String emailVerificationToken;

  private Boolean emailVerificationStatus = false;
}
