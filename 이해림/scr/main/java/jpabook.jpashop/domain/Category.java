package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Category_id")
    private Long id;

    private String name;

    /*
     * 기존의 코드 (강의)
     * 
     * @ManyToMany
     * 
     * @JoinTable(name = "category_item",
     * joinColumns = @JoinColumn(name = "category_id"),
     * inverseJoinColumns = @JoinColumn(name = "item_id"))
     */
    @OneToMany(mappedBy = "category", orphanRemoval = true, cascade = CascadeType.ALL) // 양뱡향 매핑 (과제)
    private List<Item> items = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    // ==연관관계 메서드==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
