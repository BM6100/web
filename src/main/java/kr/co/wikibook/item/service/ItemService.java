package kr.co.wikibook.item.service;

import kr.co.wikibook.item.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    List<ItemDTO> findAll();

    List<ItemDTO> findAll(List<Integer> ids);
}
