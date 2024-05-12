package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address addresss;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    /* @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
     */
}

