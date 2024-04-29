package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class CategoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_item_id")
    private Long id;

    @OneToMany(mappedBy = "categoryItem")
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "categoryItem")
    private List<Item> items = new ArrayList<>();
}
