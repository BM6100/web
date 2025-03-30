package kr.co.wikibook.cart.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.wikibook.account.helper.AccountHelper;
import kr.co.wikibook.cart.dto.CartReadDTO;
import kr.co.wikibook.cart.dto.CartRequestDTO;
import kr.co.wikibook.cart.service.CartService;
import kr.co.wikibook.item.dto.ItemDTO;
import kr.co.wikibook.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class CartController {
    private final CartService cartService;
    private final ItemService itemService;
    private final AccountHelper accountHelper;

    @GetMapping("/api/cart/items")
    public ResponseEntity<?> readAll(HttpServletRequest req) {
        Integer memberId = accountHelper.getMemberId(req);

        List<CartReadDTO> carts = cartService.findAll(memberId);

        List<Integer> itemIds = carts.stream().map(CartReadDTO::getItemId).toList();

        List<ItemDTO> items = itemService.findAll(itemIds);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/api/carts")
    public ResponseEntity<?> push(HttpServletRequest req, @RequestBody CartRequestDTO cartReq) {
        Integer memberId = accountHelper.getMemberId(req);

        CartReadDTO cart = cartService.find(memberId, cartReq.getItemId());

        if (cart == null) {
            cartService.save(cartReq.toEntity(memberId));
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/cart/items/{itemId}")
    public ResponseEntity<?> remove(HttpServletRequest req, @PathVariable("itemId") Integer itemId) {
        Integer memberId = accountHelper.getMemberId(req);

        cartService.remove(memberId, itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
