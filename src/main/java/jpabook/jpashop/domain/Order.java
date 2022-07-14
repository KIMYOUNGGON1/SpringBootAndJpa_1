package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * 예를 들어서 3개의 주문 상품(orderItems)을 주문(orders)과 함께 저장하고자 한다면
     *
     * persist(orderItemA)
     * persist(orderItemB)
     * persist(orderItemC)
     * persist(order)
     *
     * 와 같이 주문 상품에 대한 저장을 3번 하고 나서 주문을 저장한다.
     *
     * 하지만 @OneToMany annotation에 cascade 속성을 위와 같이 CascadeType.ALL로 바꿔주면
     *
     * persist(order)
     *
     * 로 해결이 될 수 있다.
     */

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    //order_date로 table 컬럼 명이 들어가게 됨.
    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER, CANCEL]

    //==연관관계 편의메서드==//

    /**
     * 양방향일때 사용하면 편리함
     * 양쪽 세팅을 하나의 클래스에서 해결 가능함
     */

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
