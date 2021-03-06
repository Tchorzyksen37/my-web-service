package pl.tchorzyksen.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.tchorzyksen.shared.dto.UserDto;
import pl.tchorzyksen.ui.model.request.UserLoginRequestModel;
import pl.tchorzyksen.SpringApplicationContext;
import pl.tchorzyksen.service.UserService;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;

  public AuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    try {
      UserLoginRequestModel creds =
          new ObjectMapper().readValue(request.getInputStream(), UserLoginRequestModel.class);

      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              creds.getEmail(), creds.getPassword(), new ArrayList<>()));

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult) {

    String userName = ((User) authResult.getPrincipal()).getUsername();

    String token =
        Jwts.builder()
            .setSubject(userName)
            .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
            .compact();

    UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
    UserDto userDto = userService.getUser(userName);

    response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);

    response.addHeader("UserID", userDto.getUserId());
  }
}
