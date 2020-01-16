package org.hjjang.springjpa.repository;

import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.hjjang.springjpa.domain.Address;
import org.hjjang.springjpa.domain.Order;
import org.hjjang.springjpa.domain.OrderStatus;

import java.time.LocalDateTime;

@Data

public class OrderSimpleQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address){
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }


}