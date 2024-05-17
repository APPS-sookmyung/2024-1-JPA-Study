package jpabook.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder // 과제 어노테이션 추가
@NoArgsConstructor // Builder을 쓰니 터지는 오류 해결을 위한 어노테이션 추가
@AllArgsConstructor // 22
// 솔직히 챗GPT에게 왜 Builder를 쓰니 오류가 터지지? 물으니 다음과 같은 답변을 받음
// uilder 클래스를 정의하고 있지만, Lombok의 @Builder 애너테이션을 사용하고 있기 때문에 충돌이 발생하고 있습니다.
// Lombok은 코드를 생성할 때 Builder 패턴을 구현하는데, 여기에 이미 Builder 클래스를 직접 정의하고 있어서 충돌이 발생하는
// 것
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public static class MemberBuilder { //Builder 클래스 정의
        private String name;
        private Address address;

        public MemberBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MemberBuilder address(Address address) {
            this.address = address;
            return this;
        }

        public Member build() {
            Member member = new Member();
            member.setName(this.name);
            member.setAddress(this.address);
            return member;

        }
    }
