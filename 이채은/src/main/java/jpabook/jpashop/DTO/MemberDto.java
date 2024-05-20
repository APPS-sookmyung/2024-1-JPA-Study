package jpabook.jpashop.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private String name;
    private String city;
    private String street;
    private String zipcode;
}