package at.atanasoski.spring.restful.example.modelassembler;

import at.atanasoski.spring.restful.example.controller.AbstractController;
import at.atanasoski.spring.restful.example.controller.UserController;
import at.atanasoski.spring.restful.example.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler extends AbstractModelAssembler<User,Long>{

    @Override
    protected Class<? extends AbstractController<User, Long>> getControllerClazz() {
        return UserController.class;
    }
}
