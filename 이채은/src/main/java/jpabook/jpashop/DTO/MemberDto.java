package jpabook.jpashop.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberDto {
    private String name;
    private String city;
    private String street;
    private String zipcode;
}