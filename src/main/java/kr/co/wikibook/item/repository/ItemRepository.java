package kr.co.wikibook.item.repository;

import kr.co.wikibook.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findAllByIdIn(List<Integer> ids);
}
