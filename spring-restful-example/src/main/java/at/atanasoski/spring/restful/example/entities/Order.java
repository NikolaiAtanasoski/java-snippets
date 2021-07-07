package at.atanasoski.spring.restful.example.entities;

import at.atanasoski.spring.restful.example.helper.OrderStatus;
import at.atanasoski.spring.restful.example.helper.PaymentType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Table(name = "ORDER_FOR_DAY")
public class Order extends BasicEntity<Order, Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_FOR_DAY_SQ")
    @SequenceGenerator(name = "ORDER_FOR_DAY_SQ", sequenceName = "ORDER_FOR_DAY_SQ", allocationSize = 1)
    private Long id;

//    @ManyToOne
//    private User orderOwner;

    @Column
    private boolean payed;

    @Column
    private PaymentType paymentType;

    @Column
    private OrderStatus status;

    @Column
    private String products;

    @Override
    public Order update(Order newOrder) {
        this.payed = newOrder.payed;
        this.paymentType = newOrder.paymentType;
        this.products = newOrder.products;
        this.status = newOrder.status;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
               "id=" + id +
               ", payed=" + payed +
               ", paymentType=" + paymentType +
               ", status=" + status +
               ", products='" + products + '\'' +
               '}';
    }
    //GETTER AND SETTER

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}