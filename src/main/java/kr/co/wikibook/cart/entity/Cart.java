package kr.co.wikibook.cart.entity;

import jakarta.persistence.*;
import kr.co.wikibook.cart.dto.CartReadDTO;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer memberId;

    @Column(nullable = false)
    private Integer itemId;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created;

    public Cart() {

    }

    public Cart(Integer memberId, Integer itemId) {
        this.memberId = memberId;
        this.itemId = itemId;
    }

    public CartReadDTO toRead() {
        return CartReadDTO.builder()
                .id(id)
                .itemId(itemId)
                .build();
    }
}
