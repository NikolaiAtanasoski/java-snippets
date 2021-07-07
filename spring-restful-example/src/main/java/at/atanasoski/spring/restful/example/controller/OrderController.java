package at.atanasoski.spring.restful.example.controller;

import at.atanasoski.spring.restful.example.entities.Order;
import at.atanasoski.spring.restful.example.helper.OrderStatus;
import at.atanasoski.spring.restful.example.modelassembler.OrderModelAssembler;
import at.atanasoski.spring.restful.example.repository.OrderRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController extends AbstractController<Order, Long> {

    public OrderController(OrderRepository repository, OrderModelAssembler orderModelAssembler) {
        super(repository, orderModelAssembler);
    }

    @Override
    @GetMapping("/orders")
    public CollectionModel<EntityModel<Order>> all() {
        return super.getAll();
    }

    @PostMapping("/orders")
    public ResponseEntity<EntityModel<Order>> newOrder(@RequestBody Order order) {
        order.setStatus(OrderStatus.IN_PROGRESS);
        return super.saveNewEntity(order);
    }

    @Override
    @GetMapping("/orders/{id}")
    public EntityModel<Order> one(@PathVariable Long id) {
        return super.getOne(id);
    }

    @DeleteMapping("/orders/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        Order order = super.findOne(id);
        if (order.getStatus() == OrderStatus.IN_PROGRESS) {
            order.setStatus(OrderStatus.CANCELLED);
            Order savedOrder = repository.save(order);
            return ResponseEntity.ok(assembler.toModel(savedOrder));
        }

        return createMethodNotAllowedResponse("You can't cancel an order with Status: " + order.getStatus());
    }

    @PutMapping("/orders/{id}/complete")
    public ResponseEntity<?> complete(@PathVariable Long id) {
        Order order = super.findOne(id);
        if (order.getStatus() == OrderStatus.IN_PROGRESS) {
            order.setStatus(OrderStatus.COMPLETED);
            Order savedOrder = repository.save(order);
            return ResponseEntity.ok(assembler.toModel(savedOrder));
        }

        return createMethodNotAllowedResponse("You can't complete an order with Status: " + order.getStatus());
    }



    @Override
    protected String getAllRel() {
        return "orders";
    }

}
