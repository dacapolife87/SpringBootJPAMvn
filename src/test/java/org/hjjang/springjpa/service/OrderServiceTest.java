package org.hjjang.springjpa.service;

import org.hjjang.springjpa.domain.Address;
import org.hjjang.springjpa.domain.Member;
import org.hjjang.springjpa.domain.Order;
import org.hjjang.springjpa.domain.OrderStatus;
import org.hjjang.springjpa.domain.item.Book;
import org.hjjang.springjpa.domain.item.Item;
import org.hjjang.springjpa.exception.NotEnoughtStockException;
import org.hjjang.springjpa.repository.OrderRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        Member member = createMember();

        Book book = createBook("JPA", 10000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품주문시 상태는 Order", OrderStatus.ORDER,getOrder.getStatus());
        assertEquals("주문한 상품 종류수가 정확해야함", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량", 10000 * orderCount, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.",8,book.getStockQuantity());

    }



    @Test(expected = NotEnoughtStockException.class)
    public void 상품주문_재고수량초과() throws Exception {
        Member member = createMember();
        Item item = createBook("JPA", 10000, 10);

        int orderCount = 11;

        orderService.order(member.getId(), item.getId(),orderCount);

        fail("재고 수량 부족 예외가 발생해야한다.");
    }

    @Test
    public void 주문취소() throws Exception {
        Member member = createMember();
        Book item = createBook("JPA", 10000, 10);

        int orderCount = 2;
        Long order = orderService.order(member.getId(), item.getId(), orderCount);

        orderService.cancelOrder(order);

        Order getOrder = orderRepository.findOne(order);

        assertEquals("주문 취소시 상태는 Cancel 이다. ", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야한다.", 10,item.getStockQuantity());
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가","123-123"));

        em.persist(member);
        return member;
    }
}
