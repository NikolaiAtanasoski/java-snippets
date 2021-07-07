package at.atanasoski.spring.restful.example.repository;

import at.atanasoski.spring.restful.example.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
