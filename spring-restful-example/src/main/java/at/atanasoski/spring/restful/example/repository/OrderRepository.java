package at.atanasoski.spring.restful.example.repository;

import at.atanasoski.spring.restful.example.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
