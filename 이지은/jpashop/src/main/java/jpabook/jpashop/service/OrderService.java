package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    //주문은 데이터 변경 필요
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);


        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        //order에서 orderitem과 delivery 모두 cascade해줬기 때문에 order만 저장해줘도
        // orderitem과 delivery가 자동으로 저장됨

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancleOrder(Long orderId){
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        //주문 취소 Order의 cancle 호출 -> OrderItem의 cancle 호출
        order.cancle();
        //JPA를 사용하기 때문에 데이터만 바꿔주면 변경내역을 감지해서 데이터베이스에 업데이트 쿼리가 자동으로 날려짐

    }


    // 검색 (껍데기만 만들어두고 나중에 추가)
    /*
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch);
}
     */

}
