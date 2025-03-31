package kr.co.wikibook.order.dto;

import kr.co.wikibook.item.dto.ItemDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderReadDTO {
    private Integer id;
    private String name;
    private String address;
    private String payment;
    private Long amount;
    private LocalDateTime created;
    private List<ItemDTO> items;
}
