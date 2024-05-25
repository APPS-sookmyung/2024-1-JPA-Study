package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id; // column으로 pk 값의 이름을 지정
    private String name;

    @Embedded
    private Address address; // 필요한 type들은 직접 class 로 생성

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}