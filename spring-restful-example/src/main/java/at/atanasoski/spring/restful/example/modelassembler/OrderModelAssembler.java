package at.atanasoski.spring.restful.example.modelassembler;

import at.atanasoski.spring.restful.example.controller.AbstractController;
import at.atanasoski.spring.restful.example.controller.OrderController;
import at.atanasoski.spring.restful.example.entities.Order;
import at.atanasoski.spring.restful.example.helper.OrderStatus;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler extends AbstractModelAssembler<Order,Long> {

    @Override
    public EntityModel<Order> toModel(Order order) {
        EntityModel<Order> orderModel = super.toModel(order);

        if(order.getStatus() == OrderStatus.IN_PROGRESS){
            orderModel.add(linkTo(methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"));
            orderModel.add(linkTo(methodOn(OrderController.class).complete(order.getId())).withRel("complete"));
        }

        return orderModel;
    }

    @Override
    protected Class<? extends AbstractController<Order, Long>> getControllerClazz() {
        return OrderController.class;
    }
}
