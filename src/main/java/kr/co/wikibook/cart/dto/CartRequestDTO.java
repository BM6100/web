package kr.co.wikibook.cart.dto;

import kr.co.wikibook.cart.entity.Cart;
import lombok.Getter;

@Getter
public class CartRequestDTO {

    private Integer itemId;

    public Cart toEntity(Integer memberId) {
        return new Cart(memberId, itemId);
    }
}
