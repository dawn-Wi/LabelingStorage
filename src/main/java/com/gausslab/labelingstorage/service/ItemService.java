package com.gausslab.labelingstorage.service;

import com.gausslab.labelingstorage.domain.Item;
import com.gausslab.labelingstorage.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> getItemList(){
        return itemRepository.findAll();
    }

    public Item submitItem(Item toAdd){
        Item itemToSave = new Item(toAdd.getAssetNumber(), toAdd.getStartDate(), toAdd.getCheckDate(), toAdd.getUseDepartment(), toAdd.getManager(),toAdd.getImage());
        return itemRepository.save(itemToSave);
    }

    public Item deleteItem(Item deleteItem){
        itemRepository.delete(deleteItem);
        return deleteItem;
    }

    public Item editItem(Item editItem){
        if(editItem.getId() != null){
            editItem.setAssetNumber(editItem.getAssetNumber());
            editItem.setStartDate(editItem.getStartDate());
            editItem.setCheckDate(editItem.getCheckDate());
            editItem.setUseDepartment(editItem.getUseDepartment());
            editItem.setManager(editItem.getManager());
            editItem.setImage(editItem.getImage());
        }
        return itemRepository.save(editItem);
    }
}
