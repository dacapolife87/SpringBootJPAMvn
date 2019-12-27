package org.hjjang.springjpa.service;

import lombok.RequiredArgsConstructor;
import org.hjjang.springjpa.domain.Delivery;
import org.hjjang.springjpa.domain.Member;
import org.hjjang.springjpa.domain.Order;
import org.hjjang.springjpa.domain.OrderItem;
import org.hjjang.springjpa.domain.item.Item;
import org.hjjang.springjpa.repository.ItemRepository;
import org.hjjang.springjpa.repository.MemberRepository;
import org.hjjang.springjpa.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());


        // 주문정보 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문취소
     */
    @Transactional
    public void cancelOrder(Long orderId){
        //주문조회
        Order order = orderRepository.findOne(orderId);

        //주문취소
        order.cancel();;

    }

    // 검색색
//    public List<Order> findOrders(OrderSearch orderSearch){
//        return orderRepository.findAll(orderSearch);
//    }
}
