package kr.co.wikibook.item.service;

import kr.co.wikibook.item.dto.ItemDTO;
import kr.co.wikibook.item.entity.Item;
import kr.co.wikibook.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseItemService implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    public List<ItemDTO> findAll() {

        return itemRepository.findAll().stream().map(Item::toRead).toList();
    }

    @Override
    public List<ItemDTO> findAll(List<Integer> ids) {
        return itemRepository.findAllByIdIn(ids).stream().map(Item::toRead).toList();
    }
}
