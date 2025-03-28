package kr.co.wikibook.cart.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartReadDTO {

    private Integer id;
    private Integer itemId;

}
