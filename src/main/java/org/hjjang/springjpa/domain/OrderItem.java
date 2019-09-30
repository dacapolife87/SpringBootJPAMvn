package org.hjjang.springjpa.domain;

import lombok.Getter;
import lombok.Setter;
import org.hjjang.springjpa.domain.item.Item;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    private int count;




}
