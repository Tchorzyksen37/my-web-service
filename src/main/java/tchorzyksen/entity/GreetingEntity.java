package tchorzyksen.entity;

import lombok.Getter;

@Getter
public class GreetingEntity {

  private final long id;

  private final String content;

  public GreetingEntity(long id, String content) {
    this.id = id;
    this.content = content;
  }
}
