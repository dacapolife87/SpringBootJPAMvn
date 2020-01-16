package org.hjjang.springjpa.repository;

import lombok.Getter;
import lombok.Setter;
import org.hjjang.springjpa.domain.OrderStatus;

@Getter @Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;

}
