package at.atanasoski.spring.restful.example.controller;

import at.atanasoski.spring.restful.example.entities.User;
import at.atanasoski.spring.restful.example.modelassembler.UserModelAssembler;
import at.atanasoski.spring.restful.example.repository.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends AbstractController<User, Long> {

    public UserController(UserRepository repository, UserModelAssembler userModelAssembler) {
        super(repository, userModelAssembler);
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/users")
    @Override
    public CollectionModel<EntityModel<User>> all() {
        return super.getAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/users")
    public ResponseEntity<EntityModel<User>> newUser(@RequestBody User newUser) {
        return super.saveNewEntity(newUser);
    }

    // Single item

    @GetMapping("/users/{id}")
    @Override
    public EntityModel<User> one(@PathVariable Long id) {
        return super.getOne(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<EntityModel<User>> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return super.replaceEntity(newUser, id);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<EntityModel<User>> deleteUser(@PathVariable Long id) {
        return super.deleteEntity(id);
    }

    @Override
    protected String getAllRel() {
        return "users";
    }
}
