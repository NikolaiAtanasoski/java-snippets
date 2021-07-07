package at.atanasoski.spring.restful.example.repository;

import at.atanasoski.spring.restful.example.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
