package kr.co.wikibook.order.repository;

import kr.co.wikibook.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findAllByOrderId(Integer orderId);
}
