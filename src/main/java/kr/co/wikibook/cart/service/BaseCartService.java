package kr.co.wikibook.cart.service;

import kr.co.wikibook.cart.dto.CartReadDTO;
import kr.co.wikibook.cart.entity.Cart;
import kr.co.wikibook.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseCartService implements CartService {

    private final CartRepository cartRepository;

    @Override
    public List<CartReadDTO> findAll(Integer memberId) {
        return cartRepository.findAllByMemberId(memberId).stream().map(Cart::toRead).toList();
    }
}
