package kr.co.wikibook.order.service;

import kr.co.wikibook.cart.service.CartService;
import kr.co.wikibook.item.dto.ItemDTO;
import kr.co.wikibook.item.service.ItemService;
import kr.co.wikibook.order.dto.OrderReadDTO;
import kr.co.wikibook.order.dto.OrderRequestDTO;
import kr.co.wikibook.order.entity.Order;
import kr.co.wikibook.order.entity.OrderItem;
import kr.co.wikibook.order.repository.OrderItemRepository;
import kr.co.wikibook.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaseOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final ItemService itemService;
    private final CartService cartService;

    @Override
    public List<OrderReadDTO> findAll(Integer memberId) {
        return orderRepository.findAllByMemberIdOrderByIdDesc(memberId).stream().map(Order::toRead).toList();
    }

    @Override
    public OrderReadDTO find(Integer id, Integer memberId) {
        Optional<Order> orderOptional = orderRepository.findByIdAndMemberId(id, memberId);

        if (orderOptional.isPresent()) {
            OrderReadDTO order = orderOptional.get().toRead();

            List<OrderItem> orderItems = orderItemService.findAll(order.getId());

            List<Integer> orderItemIds = orderItems.stream().map(OrderItem::getItemId).toList();

            List<ItemDTO> items = itemService.findAll(orderItemIds);

            order.setItems(items);

            return order;
        }

        return null;
    }

    @Override
    @Transactional
    public void order(OrderRequestDTO orderReq, Integer memberId) {
        List<ItemDTO> items = itemService.findAll(orderReq.getItemIds());
        long amount = 0L;

        for (ItemDTO item : items) {
            amount += item.getPrice() - item.getPrice().longValue() * item.getDiscountPer() / 100;
        }

        orderReq.setAmount(amount);

        Order order = orderRepository.save(orderReq.toEntity(memberId));

        List<OrderItem> newOrderItems = new ArrayList<>();

        orderReq.getItemIds().forEach((itemId) -> {
            OrderItem newOrderItem = new OrderItem(order.getId(), itemId);
            newOrderItems.add(newOrderItem);
        });

        orderItemService.saveAll(newOrderItems);

        cartService.removeAll(order.getMemberId());
    }
}
