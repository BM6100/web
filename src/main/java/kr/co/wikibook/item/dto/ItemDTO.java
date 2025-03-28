package kr.co.wikibook.item.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemDTO {
    private Integer id;
    private String name;
    private String imgPath;
    private Integer price;
    private Integer discountPer;
}
