package kr.co.wikibook.order.service;

import kr.co.wikibook.order.dto.OrderReadDTO;
import kr.co.wikibook.order.dto.OrderRequestDTO;

import java.util.List;

public interface OrderService {
    List<OrderReadDTO> findAll(Integer memberId);

    OrderReadDTO find(Integer id, Integer memberId);

    void order(OrderRequestDTO orderReq, Integer memberId);
}
