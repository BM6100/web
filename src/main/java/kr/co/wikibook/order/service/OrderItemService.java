package kr.co.wikibook.order.service;

import kr.co.wikibook.order.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> findAll(Integer orderId);

    void saveAll(List<OrderItem> orderItems);
}
