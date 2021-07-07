package at.atanasoski.spring.restful.example.modelassembler;

import at.atanasoski.spring.restful.example.controller.AbstractController;
import at.atanasoski.spring.restful.example.controller.TodoController;
import at.atanasoski.spring.restful.example.entities.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoModelAssembler extends AbstractModelAssembler<Todo,Long>{

    @Override
    protected Class<? extends AbstractController<Todo, Long>> getControllerClazz() {
        return TodoController.class;
    }
}
