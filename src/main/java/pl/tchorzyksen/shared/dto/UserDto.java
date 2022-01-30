package pl.tchorzyksen.shared.dto;

import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  private long id;

  private String userId;

  private String firstName;

  private String lastName;

  private String email;

  private String password;

  private String encryptedPassword;

  private String emailVerificationToken;

  private Boolean emailVerificationStatus = false;
}
