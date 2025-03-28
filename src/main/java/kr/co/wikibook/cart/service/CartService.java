package kr.co.wikibook.cart.service;

import kr.co.wikibook.cart.dto.CartReadDTO;
import kr.co.wikibook.cart.entity.Cart;

import java.util.List;

public interface CartService {

    List<CartReadDTO> findAll(Integer memberId);

    CartReadDTO find(Integer memberId, Integer itemId);

    void removeAll(Integer memberId);

    void remove(Integer memberId, Integer itemId);

    void save(Cart cart);
}
