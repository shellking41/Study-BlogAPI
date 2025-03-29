package org.study.studyblogapi.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.study.studyblogapi.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
