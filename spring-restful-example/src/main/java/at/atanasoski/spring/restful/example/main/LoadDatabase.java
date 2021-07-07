package at.atanasoski.spring.restful.example.main;

import at.atanasoski.spring.restful.example.entities.Order;
import at.atanasoski.spring.restful.example.entities.User;
import at.atanasoski.spring.restful.example.helper.OrderStatus;
import at.atanasoski.spring.restful.example.helper.PaymentType;
import at.atanasoski.spring.restful.example.repository.OrderRepository;
import at.atanasoski.spring.restful.example.repository.UserRepository;
import at.atanasoski.spring.restful.example.entities.Todo;
import at.atanasoski.spring.restful.example.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initTodoDatabase(TodoRepository repositoy) {
        User user1 = new User();
        user1.setName("test todo");
        user1.setUsername("testTODO");
        User user2 = new User();
        user2.setFirstName("Aubrey-Drake");
        user2.setLastName("Graham");
        user2.setUsername("drake");

        Todo todo1 = new Todo();
        todo1.setText("NEW AMK");
        todo1.setFinished(false);
        todo1.setUser(user1);
        Todo todo2 = new Todo();
        todo2.setText("Shiiinnndy");
        todo2.setFinished(true);
        todo2.setUser(user2);
        return args -> {
            logger.info("Preloading {}", todo1);
            repositoy.save(todo1);
            logger.info("Preloading {}", todo2);
            repositoy.save(todo2);
        };
    }

    @Bean
    CommandLineRunner initUserDatabase(UserRepository repositoy) {
        User user1 = new User();
        user1.setName("Lars-Daniel Hammerstein");
        user1.setUsername("laas");
        User user2 = new User();
        user2.setName("Michael Schindler");
        user2.setUsername("shindy");

        return args -> {
            logger.info("Preloading {}", user1);
            repositoy.save(user1);
            logger.info("Preloading {}", user2);
            repositoy.save(user2);
        };
    }

    @Bean
    CommandLineRunner initOrderDatabase(OrderRepository repositoy) {
        Order order1 = new Order();
        order1.setPayed(false);
        order1.setProducts("zuccini");
        order1.setPaymentType(PaymentType.CASH);
        order1.setStatus(OrderStatus.COMPLETED);
        Order order2 = new Order();
        order2.setPayed(true);
        order2.setProducts("leberkas");
        order2.setPaymentType(PaymentType.CARD);
        order2.setStatus(OrderStatus.CANCELLED);
        Order order3 = new Order();
        order3.setPayed(true);
        order3.setProducts("Pizza");
        order3.setPaymentType(PaymentType.CARD);
        order3.setStatus(OrderStatus.IN_PROGRESS);

        return args -> {
            logger.info("Preloading {}", order1);
            repositoy.save(order1);
            logger.info("Preloading {}", order2);
            repositoy.save(order2);

            logger.info("Preloading {}", order3);
            repositoy.save(order3);
        };
    }
}
