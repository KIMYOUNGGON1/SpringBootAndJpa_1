package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // Address class의 @Embeddable annotation과 같은 역할 둘 중 하나만 있어도 된다.
    private Address address;

    @OneToMany(mappedBy = "member") // 매핑되었을 뿐 연관관계의 주인은 Order class의 member다.
    private List<Order> orders = new ArrayList<>();




}
