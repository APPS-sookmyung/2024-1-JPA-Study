package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("B") //db 에서 구분을 해줄때 사용하는 값
@Getter
@Setter
public class Book extends Item {
    private String author;
    private String isbn;
}
