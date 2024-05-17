package jpabook.jpashop.DTO;

import jpabook.jpashop.domain.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter // dto안에서는 setter 자유롭게 열어도 된다는 개발자님의 티스토리 글을 봄.
public class MemberDto {
    private String name;
    private Address address;
}
