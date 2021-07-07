package at.atanasoski.spring.restful.example.controller;

import at.atanasoski.spring.restful.example.entities.Todo;
import at.atanasoski.spring.restful.example.modelassembler.TodoModelAssembler;
import at.atanasoski.spring.restful.example.repository.TodoRepository;
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
public class TodoController extends AbstractController<Todo, Long> {

    TodoController(TodoRepository repository, TodoModelAssembler assembler) {
        super(repository, assembler);
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/todos")
    public CollectionModel<EntityModel<Todo>> all() {
        return super.getAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/todos")
    public ResponseEntity<EntityModel<Todo>> newTodo(@RequestBody Todo newTodo) {
        return super.saveNewEntity(newTodo);
    }

    // Single item

    @GetMapping("/todos/{id}")
    public EntityModel<Todo> one(@PathVariable Long id) {
        return super.getOne(id);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<EntityModel<Todo>> replaceTodo(@RequestBody Todo newTodo, @PathVariable Long id) {
        return super.replaceEntity(newTodo, id);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<EntityModel<Todo>> deleteTodo(@PathVariable Long id) {
        return super.deleteEntity(id);
    }

    @Override
    protected String getAllRel() {
        return "todos";
    }

}
