package pl.tchorzyksen.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tchorzyksen.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
  UserEntity findUserByEmail(String email);
}
